import { graphql } from '$houdini';
import { superValidate } from 'sveltekit-superforms';
import { zod } from 'sveltekit-superforms/adapters';
import { error, fail, redirect } from '@sveltejs/kit';
import { StatusCodes } from 'http-status-codes';
import { logger } from '$lib/server/logger';
import { env } from '$env/dynamic/public';

import { formSchema } from './schema';
import type { PageServerLoad, Actions } from './$types';

export const load: PageServerLoad = async () => {
  return {
    form: await superValidate(zod(formSchema)),
  };
};

const addRecipeMutation = graphql(`
  mutation AddRecipe($input: RecipeInput!, $imageUid: String) {
    createRecipe(recipe: $input, imageUid: $imageUid) {
      ...All_Recipes_insert
    }
  }
`);

export const actions: Actions = {
  default: async (event) => {
    const form = await superValidate(event, zod(formSchema));
    if (!form.valid) {
      return fail(StatusCodes.BAD_REQUEST, {
        form,
      });
    }

    const { title, recipeSteps, ingredients, image, ...rest } = form.data;
    let imageUid: string | null = null;

    if (image) {
      logger.debug('Image present, starting upload...');
      logger.debug(`Image name: ${image.name}, size: ${image.size}`);
      const data = new FormData();
      data.append('image', image, image.name);

      const uploadResponse = await fetch(new URL('images', env.PUBLIC_API_URL).toString(), {
        method: 'POST',
        headers: {
          Authorization: `Bearer ${event.locals.session?.accessToken}`,
        },
        body: data,
      });

      if (!uploadResponse.ok) {
        logger.error(
          { status: uploadResponse.status, statusText: uploadResponse.statusText },
          'Failed to upload image',
        );
        return error(StatusCodes.INTERNAL_SERVER_ERROR);
      }

      logger.info('Image uploaded successfully');
      const responseData = (await uploadResponse.json()) as {
        uid?: string;
        link: string | null;
        status: string;
        message: string;
      };
      logger.debug(`Image upload response: ${JSON.stringify(responseData)}`);
      imageUid = responseData.uid ?? null;
    }

    const mutationResult = await addRecipeMutation.mutate(
      {
        input: {
          ...rest,
          steps: recipeSteps.map((step, index) => ({
            stepName: step,
            stepNumber: index,
          })),
          name: title,
          ingredients: ingredients.map(({ ingredientName, quantity, unit }) => ({
            name: ingredientName,
            quantity,
            unit,
          })),
        },
        imageUid,
      },
      { event },
    );

    if (mutationResult.errors) {
      logger.error(mutationResult.errors, 'Failed to perform addReicpe mutation');
      return error(StatusCodes.INTERNAL_SERVER_ERROR);
    }

    return redirect(StatusCodes.TEMPORARY_REDIRECT, '/');
  },
};
