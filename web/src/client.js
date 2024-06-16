import { HoudiniClient } from '$houdini';
import { browser } from '$app/environment';

import { env } from '$env/dynamic/public';

const apiBase = browser ? env.PUBLIC_API_URL : env.PUBLIC_INTERNAL_API_URL;

export default new HoudiniClient({
  url: new URL('graphql', apiBase).toString(),
  fetchParams({ session: houdiniSession }) {
    if (!houdiniSession || !houdiniSession.session) {
      return {};
    }
    return {
      headers: {
        Authorization: `Bearer ${houdiniSession.session.accessToken}`,
      },
    };
  },
});
