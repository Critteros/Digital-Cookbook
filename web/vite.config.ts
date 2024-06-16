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
});
