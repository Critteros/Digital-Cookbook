import { setSession } from '$houdini';
import type { Handle } from '@sveltejs/kit';

export const houdiniSession: Handle = async ({ event, resolve }) => {
  const { session, user } = event.locals;

  setSession(event, { session, user });

  return resolve(event);
};
