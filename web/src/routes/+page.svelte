<script lang="ts">
  import { getUserContext } from '$lib/context/userContext';

  import type { PageData } from './$houdini';
  import RecipeCard from './RecipeCard.svelte';
  import AddRecipe from './AddRecipe.svelte';

  export let data: PageData;

  const userStore = getUserContext();

  $: ({ Recipes } = data);
  $: recipes = $Recipes.data?.recipes ?? [];
</script>

{#if $userStore}
  <div class="mb-8 ml-auto">
    <AddRecipe />
  </div>
{/if}
<div class="grid grid-cols-[repeat(auto-fit,minmax(340px,auto))] justify-between gap-y-8">
  {#each recipes as recipe}
    <RecipeCard {recipe} />
  {/each}
</div>
