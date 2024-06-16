import type { User } from 'lucia';
import { getContext, setContext } from 'svelte';
import { type Writable, writable } from 'svelte/store';

type UserType = User | null;
type UserContext = Writable<UserType>;

const contextKey = 'user' as const;

export function setUserContext(user: UserType) {
  const store = writable(user);
  setContext(contextKey, store);
  return store;
}

export function getUserContext() {
  return getContext<UserContext>(contextKey);
}
