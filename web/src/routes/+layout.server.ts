import type { LayoutServerLoad } from './$types';

import { loadTranslations } from '$lib/translations';

export const load: LayoutServerLoad = async (event) => {
  const {
    url: { pathname },
    locals: { user },
  } = event;
  const initLocale = 'en';

  await loadTranslations(initLocale, pathname);

  return {
    user,
    locale: initLocale,
  };
};
