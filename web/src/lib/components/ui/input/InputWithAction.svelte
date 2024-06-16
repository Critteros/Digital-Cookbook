<script lang="ts">
  import type { HTMLInputAttributes } from 'svelte/elements';

  import { Input, type InputEvents } from './index';

  type $$Props = HTMLInputAttributes;
  type $$Events = InputEvents;

  let className: $$Props['class'] = undefined;
  export let value: $$Props['value'] = undefined;

  export { className as class };
</script>

<div class="relative flex w-full flex-col">
  <Input
    class={className}
    bind:value
    {...$$restProps}
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
  />
  <slot name="action" size="icon" />
</div>
