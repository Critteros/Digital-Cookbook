<header class="sticky top-0 flex w-full grow-0 flex-row border-b p-2">
  <div class="container mr-auto grid h-12 grid-cols-3 items-center">
    <div class="flex items-center">
      <slot name="left"></slot>
    </div>
    <div class="flex items-center justify-center">
      <slot name="center"></slot>
    </div>
    <div class="flex items-center justify-end">
      <slot name="right"></slot>
    </div>
  </div>
</header>

<style>
  header {
    background-color: hsl(var(--background) / var(--tw-bg-opacity));
  }
</style>
