import i18n, { type Config } from 'sveltekit-i18n';

const config: Config = {
  loaders: [
    {
      locale: 'en',
      key: 'common',
      loader: async () => (await import('./en/common.json')).default,
    },
    {
      locale: 'en',
      key: 'recipe',
      loader: async () => (await import('./en/recipe.json')).default,
    },
    {
      locale: 'en',
      key: 'recipeAdd',
      loader: async () => (await import('./en/recipeAdd.json')).default,
    },
  ],
};

export const { t, locale, locales, loading, loadTranslations } = new i18n(config);
