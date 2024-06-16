import { z } from 'zod';
import { env as envPrivate } from '$env/dynamic/private';
import { env as envPublic } from '$env/dynamic/public';

export function getOIDCConfiguration() {
  return z
    .object({
      OIDC_AUTHORIZE_URL: z.string().url(),
      OIDC_TOKEN_URL: z.string().url(),
      OIDC_CLIENT_ID: z.string(),
      OIDC_CLIENT_SECRET: z.string(),
      PUBLIC_URL: z.string().url(),
    })
    .transform((data) => ({
      authorizeUrl: data.OIDC_AUTHORIZE_URL,
      tokenUrl: data.OIDC_TOKEN_URL,
      clientId: data.OIDC_CLIENT_ID,
      clientSecret: data.OIDC_CLIENT_SECRET,
      publicUrl: data.PUBLIC_URL,
    }))
    .parse({ ...envPrivate, ...envPublic });
}

export const scopes = ['openid', 'profile', 'email', 'offline_access'];
