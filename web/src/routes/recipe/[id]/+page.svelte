<script lang="ts">
  import { t } from '$lib/translations';
  import { graphql } from '$houdini';

  import { Page404 } from '@/components/pages';
  import { Image } from '@/components/ui/image';
  import { DeleteButton } from '@/components/ui/button';
  import { Typography } from '@/components/ui/typography';
  import { Avatar, AvatarFallback } from '@/components/ui/avatar';

  import type { PageData } from './$houdini';
  import { getUserContext } from '@/context/userContext';
  import { goto } from '$app/navigation';

  import Reactions from './Reactions.svelte';

  export let data: PageData;

  let deleteRecipe = graphql(`
    mutation DeleteRecipe($id: ID!) {
      deleteRecipe(id: $id) {
        id @Recipe_delete
      }
    }
  `);

  $: ({ Recipe } = data);
  $: recipe = $Recipe.data?.recipe;

  const userStore = getUserContext();
</script>

{#if recipe == null}
  <Page404 />
{:else}
  {@const {
    image,
    cookingTimeMinutes,
    description,
    name,
    ingredients,
    steps,
    preparationTimeMinutes,
    servings,
  } = recipe}
  <div class="flex flex-col gap-4">
    <div class="flex flex-row">
      <Typography variant="h1">{name}</Typography>
      {#if $userStore?.id == recipe.author?.sub && $userStore != null}
        <DeleteButton
          class="ml-auto"
          on:click={() => {
            deleteRecipe.mutate({ id: recipe.id });
            goto('/');
          }}
        >
          {$t('recipe.deleteRecipe')}
        </DeleteButton>
      {/if}
    </div>
    <div class="mt-8 grid grid-cols-2 gap-12">
      <Image src={image ?? undefined} alt="Recipe image" height={400} width={600} />
      <div class="flex h-full flex-col">
        <Typography variant="p">{description}</Typography>
        <div class="mt-auto">
          <div class="flex flex-row">
            <div>
              <Typography variant="p"
                ><b>{$t('recipe.preparationTime')}:</b>
                {preparationTimeMinutes}
                {$t('recipe.minutes')}</Typography
              >
              <Typography variant="p"
                ><b>{$t('recipe.cookingTime')}:</b>
                {cookingTimeMinutes}
                {$t('recipe.minutes')}</Typography
              >
              <Typography variant="p"><b>{$t('recipe.servings')}:</b> {servings}</Typography>
            </div>
            {#if recipe.author}
              {@const { preferredUsername } = recipe.author}
              <div class="ml-auto flex flex-col">
                <Typography class="p-2">{$t('recipe.recipeAddedBy')}</Typography>
                <div class="flex flex-row items-center gap-2">
                  <Avatar>
                    <AvatarFallback>{preferredUsername.slice(0, 2)}</AvatarFallback>
                  </Avatar>
                  <Typography>{preferredUsername}</Typography>
                </div>
              </div>
            {/if}
          </div>
          <div class="mt-3">
            <Reactions {recipe} />
          </div>
        </div>
      </div>
    </div>
    <div class="grid grid-cols-2 grid-rows-[400px] gap-7">
      <div class="flex flex-col">
        <Typography variant="h2" class="min-h-0 shrink-0">{$t('recipe.ingredients')}</Typography>
        <div class="min-h-0 grow overflow-y-scroll">
          <ul class="my-6 ml-6 list-disc [&>li]:mt-2">
            {#each ingredients as ingredient}
              {@const { name, quantity, unit } = ingredient}
              <li>
                {quantity}
                {unit ? `[${unit}]` : ''}
                {name}
              </li>
            {/each}
          </ul>
        </div>
      </div>
      <div>
        <Typography variant="h2">{$t('recipe.steps')}</Typography>
        <ol class="my-6 ml-6 list-decimal [&>li]:mt-2">
          {#each steps.toSorted(({ stepNumber: aStepNumber }, { stepNumber: bStepNumber }) => aStepNumber - bStepNumber) as step}
            {@const { stepName } = step}
            <li>{stepName}</li>
          {/each}
        </ol>
      </div>
    </div>
  </div>
{/if}
