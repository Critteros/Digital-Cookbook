import { jwtDecode } from 'jwt-decode';

export type IDTokenContentsRaw = {
  sub: string;
  email: string;
  name: string;
  preferred_username: string;
};

export type IDTokenContents = Omit<IDTokenContentsRaw, 'preferred_username'> & {
  preferredUsername: string;
};

export function parseIDToken(idToken: string): IDTokenContents {
  const { preferred_username, ...other } = jwtDecode<IDTokenContentsRaw>(idToken);

  return {
    ...other,
    preferredUsername: preferred_username,
  };
}

export function isTokenExpired(token: string): boolean {
  const { exp } = jwtDecode<{ exp: number }>(token);

  return Date.now() >= exp * 1000;
}
