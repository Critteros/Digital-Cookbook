import 'dotenv/config';
import postgres from 'postgres';

import { getDbCredentials } from './config';
import { migrate } from 'drizzle-orm/postgres-js/migrator';
import { drizzle } from 'drizzle-orm/postgres-js';

const migrationClient = postgres({ ...getDbCredentials(process.env), max: 1 });
await migrate(drizzle(migrationClient), { migrationsFolder: 'drizzle' });
await migrationClient.end();
