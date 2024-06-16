<script lang="ts">
  import { routeGuard } from '$lib/utils';
  import { getUserContext } from '$lib/context/userContext';

  import type { PageData } from './$types.js';
  import AddRecipeForm from './AddRecipeForm.svelte';

  const userStore = getUserContext();

  $: routeGuard({ condition: Boolean($userStore), redirect: '/' });

  export let data: PageData;
</script>

<AddRecipeForm data={data.form} />
