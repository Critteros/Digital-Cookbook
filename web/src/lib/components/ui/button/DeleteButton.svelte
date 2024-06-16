<script lang="ts">
  import Trash from 'lucide-svelte/icons/trash-2';

  import Button from './Button.svelte';
  import type { Props, Events } from './index';

  type $$Props = Props;
  type $$Events = Events;
</script>

<Button variant="destructive" {...$$restProps} on:click on:keydown>
  <Trash class="mr-2 h-4 w-4" />
  <slot />
</Button>
