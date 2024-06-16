import Typography from './Typography.svelte';

type TypographyHtmlElements = 'h1' | 'h2' | 'h3' | 'h4' | 'p';
type Props = {
  as?: TypographyHtmlElements;
  variant?: TypographyHtmlElements;
  class?: string;
};

const elementClasses: Record<TypographyHtmlElements, string> = {
  h1: 'scroll-m-20 text-4xl font-extrabold tracking-tight lg:text-5xl',
  h2: 'scroll-m-20 border-b pb-2 text-3xl font-semibold tracking-tight transition-colors first:mt-0',
  h3: 'scroll-m-20 text-2xl font-semibold tracking-tight',
  h4: 'scroll-m-20 text-xl font-semibold tracking-tight',
  p: 'leading-7',
};

export { Typography, elementClasses, type TypographyHtmlElements, type Props };
