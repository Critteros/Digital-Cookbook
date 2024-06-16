<script lang="ts">
  import { zodClient } from 'sveltekit-superforms/adapters';
  import { type SuperValidated, superForm, fileProxy } from 'sveltekit-superforms';

  import * as Form from '$lib/components/ui/form';
  import { Input, RemovableInput, FileInput } from '$lib/components/ui/input';
  import { Textarea } from '$lib/components/ui/textarea';
  import { Button, TrashButton } from '$lib/components/ui/button';
  import { Typography } from '$lib/components/ui/typography';
  import { Separator } from '$lib/components/ui/separator';
  import { Image } from '$lib/components/ui/image';
  import { t } from '$lib/translations';
  import { goto, invalidateAll } from '$app/navigation';
  import { cache } from '$houdini';

  import { formSchema, type FormSchema } from './schema';

  export let data: SuperValidated<FormSchema>;
  let imageDataPreviewUrl: string | undefined = undefined;

  const form = superForm(data, {
    validators: zodClient(formSchema),
    dataType: 'json',
    validationMethod: 'onsubmit',
    onResult: ({ result }) => {
      if (result.type === 'redirect') {
        invalidateAll();
        cache.markStale();
        goto(result.location);
      }
    },
  });

  function addIngredient() {
    $formData.ingredients = [
      ...$formData.ingredients,
      {
        ingredientName: '',
        quantity: 1,
        unit: '',
      },
    ];
  }

  $: {
    if ($formData.image instanceof File) {
      const reader = new FileReader();
      reader.onload = () => {
        imageDataPreviewUrl = reader.result as string;
      };
      reader.readAsDataURL($formData.image);
    } else {
      imageDataPreviewUrl = undefined;
    }
  }

  const { form: formData, enhance, constraints } = form;
  const previewImage = fileProxy(formData, 'image');
</script>

<Typography variant="h1" class="mb-7">{$t('recipeAdd.addRecipe')}</Typography>
<form method="POST" use:enhance enctype="multipart/form-data">
  <div class="my-4 grid grid-cols-[1fr_10px_600px]">
    <div class="mr-4 flex flex-col gap-4">
      <Form.Field {form} name="title">
        <Form.Control let:attrs>
          <Form.Label>{$t('recipeAdd.recipeTitle')}</Form.Label>
          <Input {...attrs} bind:value={$formData.title} />
        </Form.Control>
        <Form.Description>{$t('recipeAdd.titleDescription')}</Form.Description>
        <Form.FieldErrors />
      </Form.Field>
      <Form.Field {form} name="description">
        <Form.Control let:attrs>
          <Form.Label>{$t('recipeAdd.recipeDescription')}</Form.Label>
          <Textarea {...attrs} bind:value={$formData.description} />
        </Form.Control>
        <Form.FieldErrors />
      </Form.Field>
      <Form.Field {form} name="image" class="mt-auto">
        <Form.Control let:attrs>
          <Form.Label>{$t('recipeAdd.recipeImage')}</Form.Label>
          <FileInput {...attrs} bind:files={$previewImage} accept=".png, .jpg, .jpeg" />
        </Form.Control>
        <Form.Description>{$t('recipeAdd.imageDescription')}</Form.Description>
      </Form.Field>
    </div>
    <Separator orientation="vertical" />
    <Image
      src={imageDataPreviewUrl}
      alt="Recipe image"
      class="justify-self-end"
      height={400}
      width={600}
    />
  </div>
  <div class="grid grid-cols-3 grid-rows-1 gap-4">
    <Form.Field {form} name="cookingTimeMinutes">
      <Form.Control let:attrs>
        <Form.Label>{$t('recipeAdd.cookingTime')}</Form.Label>
        <Input
          {...attrs}
          bind:value={$formData.cookingTimeMinutes}
          min={$constraints.cookingTimeMinutes?.min}
          max={$constraints.cookingTimeMinutes?.max}
          type="number"
        />
      </Form.Control>
      <Form.Description>{$t('recipeAdd.cookingTimeDescription')}</Form.Description>
      <Form.FieldErrors />
    </Form.Field>
    <Form.Field {form} name="preparationTimeMinutes">
      <Form.Control let:attrs>
        <Form.Label>{$t('recipeAdd.preparationTime')}</Form.Label>
        <Input
          {...attrs}
          bind:value={$formData.preparationTimeMinutes}
          min={$constraints.preparationTimeMinutes?.min}
          max={$constraints.preparationTimeMinutes?.max}
          type="number"
        />
      </Form.Control>
      <Form.Description>{$t('recipeAdd.preparationTimeDescription')}</Form.Description>
      <Form.FieldErrors />
    </Form.Field>
    <Form.Field {form} name="servings">
      <Form.Control let:attrs>
        <Form.Label>
          {$t('recipeAdd.servings')}
        </Form.Label>
        <Input
          {...attrs}
          bind:value={$formData.servings}
          min={$constraints.servings?.min}
          max={$constraints.servings?.max}
          type="number"
        />
      </Form.Control>
      <Form.Description>{$t('recipeAdd.servingsDescription')}</Form.Description>
      <Form.FieldErrors />
    </Form.Field>
  </div>
  <div class="grid grid-cols-[1fr_10px_1fr] grid-rows-1">
    <Form.Fieldset {form} name="ingredients" class="mr-4">
      <Form.Legend>
        <Typography variant="h3">
          {$t('recipeAdd.ingredients')}
        </Typography>
      </Form.Legend>
      {#each $formData.ingredients as ingredientForm, i}
        <Form.Fieldset
          {form}
          class="grid grid-cols-[5fr_1fr_1fr_auto] grid-rows-1 items-start justify-center gap-3 gap-x-4 space-y-0"
          name={`ingredients[${i}]`}
        >
          <Form.Field {form} class="space-y-0" name={`ingredients[${i}].ingredientName`}>
            <Form.Control let:attrs>
              <Form.Label>{$t('recipeAdd.ingredientName')}</Form.Label>
              <Input {...attrs} bind:value={$formData.ingredients[i].ingredientName} />
            </Form.Control>
            <Form.Description>{$t('recipeAdd.ingredientNameDescription')}</Form.Description>
            <Form.FieldErrors />
          </Form.Field>
          <Form.Field {form} class="space-y-0" name={`ingredients[${i}].quantity`}>
            <Form.Control let:attrs>
              <Form.Label>{$t('recipeAdd.quantity')}</Form.Label>
              <Input
                {...attrs}
                bind:value={ingredientForm.quantity}
                min={$constraints.ingredients?.quantity?.min}
                max={$constraints.ingredients?.quantity?.max}
                type="number"
              />
            </Form.Control>
            <Form.Description>{$t('recipeAdd.quantityDescription')}</Form.Description>
            <Form.FieldErrors />
          </Form.Field>
          <Form.Field {form} class="space-y-0" name={`ingredients[${i}].unit`}>
            <Form.Control let:attrs>
              <Form.Label>{$t('recipeAdd.unit')}</Form.Label>
              <Input {...attrs} bind:value={$formData.ingredients[i].unit} />
            </Form.Control>
            <Form.Description>{$t('recipeAdd.unitDescription')}</Form.Description>
            <Form.FieldErrors />
          </Form.Field>
          <TrashButton
            class="relative right-0 top-3 -translate-y-3 transform self-center justify-self-end"
            on:click={() => {
              $formData.ingredients = $formData.ingredients.filter((_, index) => index !== i);
            }}
          />
        </Form.Fieldset>
      {/each}
      <Button on:click={addIngredient}>
        {$t('recipeAdd.addIngredient')}
      </Button>
      <Form.FieldErrors />
    </Form.Fieldset>
    <Separator orientation="vertical" class="mx-auto" />
    <Form.Fieldset {form} name="recipeSteps" class="ml-4">
      <Form.Legend>
        <Typography variant="h3">
          {$t('recipeAdd.recipeSteps')}
        </Typography>
      </Form.Legend>
      {#each $formData.recipeSteps as step, i}
        <Form.ElementField {form} name={`recipeSteps[${i}]`}>
          <Form.Control let:attrs>
            <Form.Label>{$t('recipeAdd.step')} {i + 1}</Form.Label>
            <RemovableInput
              {...attrs}
              bind:value={$formData.recipeSteps[i]}
              on:remove={() => {
                $formData.recipeSteps = $formData.recipeSteps.filter((_, index) => index !== i);
              }}
            />
            <Form.Description>{$t('recipeAdd.stepDescription')} {i + 1}</Form.Description>
          </Form.Control>
          <Form.FieldErrors />
        </Form.ElementField>
      {/each}
      <Button
        on:click={() => {
          $formData.recipeSteps = [...$formData.recipeSteps, ''];
        }}
      >
        {$t('recipeAdd.addStep')}
      </Button>
      <Form.FieldErrors />
    </Form.Fieldset>
  </div>
  <Form.Button class="my-16 w-full">{$t('recipeAdd.add')}</Form.Button>
</form>
