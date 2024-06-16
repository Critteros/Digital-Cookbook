import { redirect, type RequestEvent } from '@sveltejs/kit';
import { StatusCodes } from 'http-status-codes';
import { dev } from '$app/environment';

import * as authentik from '$lib/server/oidc/authentik';
import { getOIDCConfiguration } from '$lib/server/oidc/authentik/configuration';
import { StateCookie } from '$lib/server/oidc/authentik';

export async function GET(event: RequestEvent): Promise<Response> {
  const { publicUrl } = getOIDCConfiguration();
  const { url, state, codeVerifier } = await authentik.getAuthorizationUrl({
    baseUri: publicUrl,
  });
  const referer = event.request.headers.get('referer');

  const cookie = new StateCookie({ state, codeVerifier, referer });

  event.cookies.set(StateCookie.cookieName, cookie.toString(), {
    path: '/',
    secure: !dev,
    httpOnly: true,
    maxAge: 60 * 10, // 10 minutes,
    sameSite: 'lax',
  });

  return redirect(StatusCodes.MOVED_TEMPORARILY, url.toString());
}
