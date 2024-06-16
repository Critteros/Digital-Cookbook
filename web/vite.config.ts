import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vitest/config';
import houdini from 'houdini/vite';

export default defineConfig({
  plugins: [sveltekit(), houdini()],
  test: {
    include: ['src/**/*.{test,spec}.{js,ts}'],
  },
  server: {
    proxy: {
      '/api': 'http://localhost:8080',
    },
  },
  resolve: {
    alias: {
      $houdini: './$houdini',
    },
  },
  optimizeDeps: {
    include: [
      'mode-watcher',
      'svelte-radix',
      'sveltekit-i18n',
      'bits-ui',
      'lucide-svelte/icons/plus',
      'lucide-svelte/icons/trash-2',
      'lucide-svelte',
      'svelte-radix/Sun.svelte',
      'svelte-radix/Moon.svelte',
      '@iconify/svelte/dist/OfflineIcon.svelte',
      'svelte-radix/DotFilled.svelte',
      'svelte-radix/ChevronRight.svelte',
      'svelte-radix/Check.svelte',
      'sveltekit-superforms/adapters',
      'sveltekit-superforms',
      'zod',
      'formsnap',
      '@sveltekit-i18n/parser-default',
      'dequal',
      'nanoid/non-secure',
      '@internationalized/date',
      '@floating-ui/dom',
      'focus-trap',
      'arktype',
      '@sinclair/typebox/compiler',
      '@sinclair/typebox',
      'valibot',
      '@gcornut/valibot-json-schema',
      'yup',
      'zod-to-json-schema',
      '@vinejs/vine',
      '@exodus/schemasafe',
      'ts-deepmerge',
      'memoize-weak',
      'just-clone',
      'tailwind-variants',
      'clsx',
      'tailwind-merge',
      'lucide-svelte/icons/thumbs-up',
      'lucide-svelte/icons/thumbs-down',
    ],
  },
});
