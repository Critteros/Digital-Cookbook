import { sequence } from '@sveltejs/kit/hooks';
import { refreshTokens, createSessionHandler, houdiniSession } from '$lib/server/hooks';

export const handle = sequence(createSessionHandler, refreshTokens, houdiniSession);
