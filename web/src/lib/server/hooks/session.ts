import { getLucia, endSession } from '$lib/server/auth';
import type { Handle } from '@sveltejs/kit';

export const createSessionHandler: Handle = async ({ event, resolve }) => {
  const lucia = getLucia();
  const sessionId = event.cookies.get(lucia.sessionCookieName);
  if (!sessionId) {
    event.locals.user = null;
    event.locals.session = null;
    return resolve(event);
  }

  const { session, user } = await lucia.validateSession(sessionId);
  if (session && session.fresh) {
    const sessionCookie = lucia.createSessionCookie(session.id);
    // sveltekit types deviates from the de-facto standard
    // you can use 'as any' too
    event.cookies.set(sessionCookie.name, sessionCookie.value, {
      path: '.',
      ...sessionCookie.attributes,
    });
  }
  if (!session) {
    await endSession({ event });
  }
  event.locals.user = user;
  event.locals.session = session;
  return resolve(event);
};
