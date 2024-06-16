import { redirect, type RequestEvent } from '@sveltejs/kit';
import { StatusCodes } from 'http-status-codes';

import {
  parseRedirectParams,
  parseIDToken,
  getUserById,
  createUserFromIDToken,
} from '$lib/server/oidc';
import { StateCookie, exchangeCodeForTokens } from '$lib/server/oidc/authentik';
import { getOIDCConfiguration } from '$lib/server/oidc/authentik/configuration';
import { getLucia } from '$lib/server/auth';

export async function GET(event: RequestEvent): Promise<Response> {
  const lucia = getLucia();
  const { publicUrl } = getOIDCConfiguration();
  const { state, code } = parseRedirectParams(event.url);
  const cookieRaw = event.cookies.get(StateCookie.cookieName) ?? null;
  event.cookies.delete(StateCookie.cookieName, { path: '/' });

  if (!state || !code || !cookieRaw) {
    return new Response(null, {
      status: StatusCodes.BAD_REQUEST,
    });
  }
  const { codeVerifier, state: storedState, referer } = new StateCookie(cookieRaw);
  if (storedState !== state) {
    return new Response(null, { status: StatusCodes.BAD_REQUEST });
  }

  const tokens = await exchangeCodeForTokens({
    code,
    codeVerifier,
    baseUri: publicUrl,
  });
  const identity = parseIDToken(tokens.idToken);
  const user = (await getUserById(identity.sub)) ?? (await createUserFromIDToken(identity));
  const session = await lucia.createSession(user.id, tokens);
  const sessionCookie = lucia.createSessionCookie(session.id);
  event.cookies.set(sessionCookie.name, sessionCookie.value, {
    path: '.',
    ...sessionCookie.attributes,
  });

  return redirect(StatusCodes.MOVED_TEMPORARILY, referer ?? '/');
}
