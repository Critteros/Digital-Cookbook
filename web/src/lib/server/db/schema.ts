import { pgTable, text, timestamp, varchar } from 'drizzle-orm/pg-core';

export const userTable = pgTable('user', {
  id: varchar('id', { length: 1024 }).primaryKey(),
  username: varchar('username', { length: 512 }).notNull(),
  email: varchar('email', { length: 512 }).notNull(),
});

export const sessionTable = pgTable('session', {
  id: varchar('id', { length: 1024 }).primaryKey(),
  userId: varchar('user_id', { length: 1024 })
    .notNull()
    .references(() => userTable.id),
  expiresAt: timestamp('expires_at', {
    withTimezone: true,
    mode: 'date',
  }).notNull(),
  accessToken: text('access_token').notNull(),
  refreshToken: text('refresh_token').notNull(),
  idToken: text('id_token').notNull(),
});
