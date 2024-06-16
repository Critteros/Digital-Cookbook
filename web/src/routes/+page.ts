import type { RecipesVariables } from './$houdini';

export const _RecipesVariables: RecipesVariables = ({ url }) => {
  const search = url.searchParams.get('q') ?? '';

  return {
    search,
  };
};
