import { OAuth2RequestError, type TokenResponseBody } from 'oslo/oauth2';

import { logger } from '$lib/server/logger';

import { getOIDCConfiguration } from './configuration';
import { getOAuth2Client } from './client';

export async function exchangeCodeForTokens({
  code,
  codeVerifier,
  baseUri,
}: {
  code: string;
  codeVerifier: string;
  baseUri: string | URL;
}) {
  const client = getOAuth2Client(baseUri);
  const { clientSecret } = getOIDCConfiguration();
  const {
    access_token: accessToken,
    refresh_token: refreshToken,
    id_token: idToken,
    expires_in: expiresIn,
  } = await client.validateAuthorizationCode<
    TokenResponseBody & { id_token: string; refresh_token: string; expires_in: number }
  >(code, {
    credentials: clientSecret,
    codeVerifier,
    authenticateWith: 'request_body',
  });

  return { accessToken, refreshToken, idToken, expiresIn };
}

export async function refreshAccessToken({ refreshToken }: { refreshToken: string }) {
  const { clientSecret } = getOIDCConfiguration();
  const client = getOAuth2Client();

  try {
    const { access_token, refresh_token } = await client.refreshAccessToken<
      TokenResponseBody & { refresh_token: string }
    >(refreshToken, {
      credentials: clientSecret,
      authenticateWith: 'request_body',
    });
    return { accessToken: access_token, refreshToken: refresh_token };
  } catch (e) {
    if (e instanceof OAuth2RequestError) {
      return null;
    }
    logger.error('Failed to refresh access token', e);
    throw e;
  }
}
