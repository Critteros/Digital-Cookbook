<script lang="ts">
  import ThumbsUpIcon from 'lucide-svelte/icons/thumbs-up';
  import ThumbsDownIcon from 'lucide-svelte/icons/thumbs-down';

  import { Toggle } from '$lib/components/ui/toggle';
  import { t } from '$lib/translations';
  import { getUserContext } from '$lib/context/userContext';

  export let reactionType: 'like' | 'dislike';
  export let active: boolean = false;
  export let count: number;

  const userStore = getUserContext();
</script>

<Toggle
  class="flex flex-row items-center gap-2 border-2 border-solid border-secondary"
  bind:pressed={active}
  on:click
  disabled={!Boolean($userStore)}
>
  {#if reactionType === 'like'}
    <ThumbsUpIcon />
  {:else}
    <ThumbsDownIcon />
  {/if}
  {reactionType === 'like' ? $t('recipe.like') : $t('recipe.dislike')}
  <span class="mx-1.5">
    {count}
  </span>
</Toggle>
