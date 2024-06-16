<script lang="ts">
  import '../app.pcss';
  import { ModeWatcher } from 'mode-watcher';
  import { setClientSession, extractSession } from '$houdini';

  import { setUserContext } from '$lib/context/userContext';

  import AppBar from './AppBar.svelte';
  import type { LayoutData } from './$houdini';

  export let data: LayoutData;
  $: ({ user } = data);

  const userInfo = setUserContext(user);
  // @ts-expect-error Houdini needs to improve it's typing
  $: setClientSession(extractSession(data));
  $: $userInfo = user;
</script>

<ModeWatcher />

<AppBar />
<main class="container flex grow flex-col py-8">
  <slot />
</main>
