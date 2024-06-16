import { loadTranslations } from '$lib/translations';

import type { LayoutLoad } from './$types';

export const load: LayoutLoad = async ({ data: { locale, user }, url: { pathname } }) => {
  await loadTranslations(locale, pathname);

  return {
    user,
  };
};
