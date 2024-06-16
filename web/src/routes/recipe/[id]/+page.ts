import type { RecipeVariables } from './$houdini';

export const _RecipeVariables: RecipeVariables = async ({ params }) => {
  const recipeId = params.id;

  return {
    recipeId,
  };
};
