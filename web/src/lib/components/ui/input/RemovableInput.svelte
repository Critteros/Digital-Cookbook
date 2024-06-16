<script lang="ts">
  import { createEventDispatcher } from 'svelte';
  import type { HTMLInputAttributes } from 'svelte/elements';
  import { Trash2 } from 'lucide-svelte';

  import { Button } from '$lib/components/ui/button';

  import { InputWithAction, type RemovableInputEvents } from './index';

  type $$Props = HTMLInputAttributes;
  type $$Events = RemovableInputEvents;

  const dispatch = createEventDispatcher<{
    remove: undefined;
  }>();

  let className: $$Props['class'] = undefined;
  export let value: $$Props['value'] = undefined;

  export { className as class };
</script>

<InputWithAction
  bind:value
  {...$$restProps}
  class={className}
  on:blur
  on:change
  on:click
  on:focus
  on:focusin
  on:focusout
  on:keydown
  on:keypress
  on:keyup
  on:mouseover
  on:mouseenter
  on:mouseleave
  on:mousemove
  on:paste
  on:input
>
  <Button
    slot="action"
    variant="destructive"
    size="icon"
    class="absolute right-0 top-2/4 -translate-y-2/4"
    on:click={() => {
      dispatch('remove');
    }}
  >
    <Trash2 />
  </Button>
</InputWithAction>
