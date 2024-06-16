<script lang="ts">
  import { Skeleton } from '@/components/ui/skeleton';

  import type { ImageProps } from './index';

  type $$Props = ImageProps;

  export let width: string | number = '';
  export let height: string | number = '';
  export let src: string | undefined = undefined;
  export let alt: string;
  let className: string | undefined = undefined;

  let loaded = false;

  $: widthStyle = typeof width == 'number' ? `width: ${width}px` : `width: ${width}`;
  $: heightStyle = typeof height == 'number' ? `height: ${height}px` : `height: ${height}`;
  $: imgStyle = [width ? widthStyle : [], height ? heightStyle : []].flat().join(';');

  function onload(el: HTMLImageElement) {
    loaded = true;
  }

  export { className as class };
</script>

<img
  src={src ?? 'https://image-placeholder.com/images/actual-size/1366x768.png'}
  {alt}
  use:onload
  class={className}
  class:loaded
  style={imgStyle}
/>

{#if !loaded}
  <Skeleton style={imgStyle} />
{/if}

<style>
  img {
    opacity: 0;
    display: block;
    width: 100%;
    height: auto;
    display: none;
  }
  img.loaded {
    opacity: 1;
    display: block;
  }
</style>
