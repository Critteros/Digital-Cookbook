import 'dotenv/config';

import type { Config } from 'drizzle-kit';

import { getDbCredentials } from '$lib/server/db/config';

const dbCredentials = getDbCredentials(process.env);

export default {
  schema: './src/lib/server/db/schema.ts',
  out: './drizzle',
  driver: 'pg',
  dbCredentials,
} satisfies Config;
