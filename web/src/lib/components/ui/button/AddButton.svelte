<script lang="ts">
  import Plus from 'lucide-svelte/icons/plus';

  import Button from './Button.svelte';
  import type { Props, Events } from './index';

  type $$Props = Props;
  type $$Events = Events;
</script>

<Button {...$$restProps} on:click on:keydown>
  <Plus class="mr-2 h-4 w-4" />
  <slot />
</Button>
