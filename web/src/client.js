import { HoudiniClient } from '$houdini';
import { env } from '$env/dynamic/public';

export default new HoudiniClient({
  url: new URL('graphql', env.PUBLIC_API_URL).toString(),
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
