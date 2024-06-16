import { z } from 'zod';

const dbSchema = z
  .object({
    DB_HOST: z.string(),
    DB_PORT: z.string().regex(/^\d+$/).transform(Number),
    DB_USER: z.string(),
    DB_PASSWORD: z.string(),
    DB_NAME: z.string(),
  })
  .transform((env) => ({
    host: env.DB_HOST,
    port: env.DB_PORT,
    username: env.DB_USER,
    password: env.DB_PASSWORD,
    database: env.DB_NAME,
  }));

export function getDbCredentials(enviroment: unknown) {
  return dbSchema.parse(enviroment);
}
