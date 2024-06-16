<script lang="ts">
  import { Trash2 } from 'lucide-svelte';

  import { Button, type Props, type Events } from './index';

  type $$Props = Props;
  type $$Events = Events;
</script>

<Button size="icon" variant="destructive" on:click {...$$restProps}>
  <Trash2 />
</Button>
