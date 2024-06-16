<script lang="ts">
  import { fragment, graphql, type RecipeDisplay } from '$houdini';

  import * as Card from '@/components/ui/card';
  import { Image } from '@/components/ui/image';

  export let recipe: RecipeDisplay;

  $: data = fragment(
    recipe,
    graphql(`
      fragment RecipeDisplay on Recipe {
        id
        name
        description
        image
        preparationTimeMinutes
        cookingTimeMinutes
        totalTimeMinutes
        servings
      }
    `),
  );

  $: ({ id, name, image, description } = $data);
</script>

<a href={`/recipe/${id}`} class="inline">
  <Card.Root class="w-[350px] hover:bg-accent active:bg-accent/90">
    <Card.Header>
      <Card.Title>{name}</Card.Title>
      <Card.Description class="h-8 text-ellipsis">{description}</Card.Description>
    </Card.Header>
    <Card.Content>
      <Image class="w-full" src={image ?? undefined} alt={name} height={200} width={300} />
    </Card.Content>
  </Card.Root>
</a>
