import { endSession, updateSessionTokens } from '$lib/server/auth';
import type { Handle } from '@sveltejs/kit';

import { isTokenExpired } from '$lib/server/oidc';
import { refreshAccessToken } from '$lib/server/oidc/authentik';

export const refreshTokens: Handle = async ({ event, resolve }) => {
  if (!event.locals.session) {
    return resolve(event);
  }
  const { accessToken, refreshToken } = event.locals.session;

  if (!isTokenExpired(accessToken)) {
    return resolve(event);
  }

  const newTokens = await refreshAccessToken({ refreshToken });
  if (!newTokens) {
    console.warn('Could not refresh tokens');
    await endSession({ event });
    return resolve(event);
  }
  const { accessToken: newAccessToken, refreshToken: newRefreshToken } = newTokens;
  await updateSessionTokens({ event, accessToken: newAccessToken, refreshToken: newRefreshToken });
  return resolve(event);
};
