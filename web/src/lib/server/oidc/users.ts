import { getDbClient, userTable } from '$lib/server/db';

import type { IDTokenContents } from './tokens';

export async function createUserFromIDToken({ sub, preferredUsername, email }: IDTokenContents) {
  const db = getDbClient();
  const createdUsers = await db
    .insert(userTable)
    .values({
      id: sub,
      email,
      username: preferredUsername,
    })
    .returning();

  return createdUsers[0];
}

export async function getUserById(id: string) {
  const db = getDbClient();
  const user = await db.query.userTable.findFirst({
    where: (users, { eq }) => eq(users.id, id),
  });

  return user ?? null;
}
