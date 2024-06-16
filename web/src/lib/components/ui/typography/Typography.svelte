<script lang="ts">
  import { cn } from '@/utils';
  import type { R } from '@/types';
  import { elementClasses, type Props } from './index';

  type $$Props = Props;

  let className: $$Props['class'] = undefined;
  export let variant: R<$$Props['variant']> = 'p';
  export let as: $$Props['as'] = 'p';

  export { className as class };
</script>

<svelte:element this={as} class={cn(elementClasses[variant], className)}>
  <slot />
</svelte:element>
