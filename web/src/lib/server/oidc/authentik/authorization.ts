import { z } from 'zod';
import { generateCodeVerifier, generateState } from 'oslo/oauth2';

import { getOAuth2Client } from './client';
import { scopes } from './configuration';

export class StateCookie {
  public readonly state: string;
  public readonly codeVerifier: string;
  public readonly referer: string | null;

  private static stateSchema = z.object({
    state: z.string(),
    codeVerifier: z.string(),
    referer: z.string().nullable().default(null),
  });

  public static readonly cookieName = 'authentik_oauth_state';
  constructor(input: { state: string; codeVerifier: string; referer?: string | null } | string) {
    if (typeof input === 'string') {
      const obj = StateCookie.stateSchema.parse(JSON.parse(input));
      this.state = obj.state;
      this.codeVerifier = obj.codeVerifier;
      this.referer = obj.referer ?? null;
      return;
    }
    this.state = input.state;
    this.codeVerifier = input.codeVerifier;
    this.referer = input.referer ?? null;
  }

  public toString() {
    return JSON.stringify({
      state: this.state,
      codeVerifier: this.codeVerifier,
      referer: this.referer,
    });
  }
}

export async function getAuthorizationUrl({ baseUri }: { baseUri: URL | string }) {
  const state = generateState();
  const codeVerifier = generateCodeVerifier();
  const oauth2Client = getOAuth2Client(baseUri);

  const url = await oauth2Client.createAuthorizationURL({
    state,
    codeVerifier,
    scopes,
  });

  return { url, state, codeVerifier };
}
