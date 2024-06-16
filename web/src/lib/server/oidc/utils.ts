export function parseRedirectParams(url: URL) {
  const code = url.searchParams.get('code');
  const state = url.searchParams.get('state');
  return { code, state };
}
