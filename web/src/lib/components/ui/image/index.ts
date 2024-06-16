import Image from './Image.svelte';

type HeightMixin = { height: number | string; width?: string | number };
type WidthMixin = { width: number | string; height?: string | number };

export type ImageProps = (HeightMixin | WidthMixin) & {
  alt: string;
  src?: string;
  class?: string;
};

export { Image };
