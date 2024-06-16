import { redirect, type RequestEvent } from '@sveltejs/kit';

import { endSession } from '$lib/server/auth';
import { StatusCodes } from 'http-status-codes';

export async function GET(event: RequestEvent) {
  const referer = event.request.headers.get('referer') ?? '/';
  await endSession({ event });

  return redirect(StatusCodes.MOVED_TEMPORARILY, referer);
}
