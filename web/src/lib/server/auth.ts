import { Lucia } from 'lucia';
import { dev } from '$app/environment';
import memoizee from 'memoizee';

import type { RequestEvent } from '@sveltejs/kit';
import { eq } from 'drizzle-orm/sql/expressions/conditions';

import { createDbAdapter, sessionTable, getDbClient } from './db';

export const getLucia = memoizee(() => {
  return new Lucia(createDbAdapter(), {
    sessionCookie: {
      attributes: {
        secure: !dev,
      },
    },
    getSessionAttributes: ({ accessToken, idToken, refreshToken }) => ({
      accessToken,
      idToken,
      refreshToken,
    }),
    getUserAttributes: ({ id, email, username }) => ({
      id,
      email,
      username,
    }),
  });
});

declare module 'lucia' {
  interface Register {
    Lucia: ReturnType<typeof getLucia>;
    DatabaseSessionAttributes: DatabaseSessionAttributes;
    DatabaseUserAttributes: DatabaseUserAttributes;
  }
}

interface DatabaseSessionAttributes {
  accessToken: string;
  refreshToken: string;
  idToken: string;
}

interface DatabaseUserAttributes {
  id: string;
  email: string;
  username: string;
}

export async function endSession({ event }: { event: RequestEvent }) {
  const lucia = getLucia();

  const sessionId = event.cookies.get(lucia.sessionCookieName);
  if (sessionId) {
    await lucia.invalidateSession(sessionId);
  }
  const sessionCookie = lucia.createBlankSessionCookie();
  event.cookies.set(sessionCookie.name, sessionCookie.value, {
    path: '.',
    ...sessionCookie.attributes,
  });
  event.locals.user = null;
  event.locals.session = null;
}

export async function updateSessionTokens({
  event,
  accessToken,
  refreshToken,
}: {
  event: RequestEvent;
  accessToken: string;
  refreshToken: string;
}) {
  const db = getDbClient();

  const sessionId = event.locals.session?.id;
  if (!sessionId || !event.locals.session) {
    throw new Error('Session identifier not found');
  }
  await db
    .update(sessionTable)
    .set({
      accessToken,
      refreshToken,
    })
    .where(eq(sessionTable.id, sessionId));
  event.locals.session.refreshToken = refreshToken;
  event.locals.session.accessToken = accessToken;
}
