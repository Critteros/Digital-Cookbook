import { z } from 'zod';
import { env } from '$env/dynamic/private';

export function getOIDCConfiguration() {
  return z
    .object({
      OIDC_AUTHORIZE_URL: z.string().url(),
      OIDC_TOKEN_URL: z.string().url(),
      OIDC_CLIENT_ID: z.string(),
      OIDC_CLIENT_SECRET: z.string(),
    })
    .transform((data) => ({
      authorizeUrl: data.OIDC_AUTHORIZE_URL,
      tokenUrl: data.OIDC_TOKEN_URL,
      clientId: data.OIDC_CLIENT_ID,
      clientSecret: data.OIDC_CLIENT_SECRET,
    }))
    .parse(env);
}

export const scopes = ['openid', 'profile', 'email', 'offline_access'];
