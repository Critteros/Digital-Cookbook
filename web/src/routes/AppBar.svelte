<script lang="ts">
  import { t } from '@/translations';
  import { AppBar } from '@/components/ui/appbar';
  import { Java } from '@/components/icons';
  import { Typography } from '@/components/ui/typography';
  import { type FormInputEvent, Input } from '@/components/ui/input';
  import { MagnifyingGlass } from 'svelte-radix';
  import { ThemeToggle } from '@/components/ui/theme-toggle';
  import { Button } from '@/components/ui/button';
  import { page } from '$app/stores';
  import { goto } from '$app/navigation';

  import { getUserContext } from '$lib/context/userContext';

  import ProfileDropdown from './ProfileDropdown.svelte';

  const user = getUserContext();
  $: query = $page.url.searchParams.get('q');

  function onSearchChange(e: FormInputEvent) {
    const value = e.currentTarget.value;
    const url = new URL($page.url);
    url.searchParams.set('q', value);
    goto(url);
  }
</script>

<AppBar>
  <a slot="left" href="/" class="flex items-center justify-center gap-2">
    <Java />
    <Typography variant="h3">JavaFlavors</Typography>
  </a>
  <div slot="center" class="relative flex w-full flex-col">
    <Input placeholder={$t('common.search')} on:change={onSearchChange} value={query} />
    <span class="absolute right-1 top-2/4 -translate-y-2/4">
      <MagnifyingGlass />
    </span>
  </div>
  <div slot="right" class="flex items-center justify-center gap-2">
    <ThemeToggle />
    {#if $user}
      <ProfileDropdown />
    {:else}
      <Button href="/login">{$t('common.login')}</Button>
    {/if}
  </div>
</AppBar>
