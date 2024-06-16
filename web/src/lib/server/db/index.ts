import postgres from 'postgres';
import { drizzle } from 'drizzle-orm/postgres-js';
import { DrizzlePostgreSQLAdapter } from '@lucia-auth/adapter-drizzle';
import memoizee from 'memoizee';

import { env } from '$env/dynamic/private';

import { getDbCredentials } from './config';
import * as schema from './schema';
export * from './schema';

export const getDbClient = memoizee(() => {
  const config = getDbCredentials(env);
  const nativeClient = postgres(config);
  return drizzle(nativeClient, { schema });
});

export function createDbAdapter() {
  return new DrizzlePostgreSQLAdapter(getDbClient(), schema.sessionTable, schema.userTable);
}
