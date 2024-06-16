import { OAuth2Client } from 'oslo/oauth2';
import { getOIDCConfiguration } from './configuration';

export function getOAuth2Client(baseUri?: string | URL) {
  const { authorizeUrl, tokenUrl, clientId } = getOIDCConfiguration();
  if (!baseUri) {
    return new OAuth2Client(clientId, authorizeUrl, tokenUrl);
  }

  const redirectUri = new URL('/login/callback', baseUri);
  return new OAuth2Client(clientId, authorizeUrl, tokenUrl, {
    redirectURI: redirectUri.toString(),
  });
}
