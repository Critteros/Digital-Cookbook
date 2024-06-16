<script lang="ts">
  import { fragment, graphql, type RecipeReactionsFragment } from '$houdini';

  import { Typography } from '@/components/ui/typography';

  import ReactionButton from './ReactionButton.svelte';

  export let recipe: RecipeReactionsFragment;

  $: data = fragment(
    recipe,
    graphql(`
      fragment RecipeReactionsFragment on Recipe {
        id
        reactions {
          downvoteCount
          upvoteCount
          userReaction
        }
      }
    `),
  );

  $: recipeId = $data.id;
  $: ({ upvoteCount, downvoteCount, userReaction } = $data.reactions);

  const updateRecipeReaction = graphql(`
    mutation UpdateRecipeReaction($recipeId: ID!, $reaction: ReactionType) {
      updateRecipeReaction(recipeId: $recipeId, reaction: $reaction) {
        id
        reactions {
          downvoteCount
          upvoteCount
          userReaction
        }
      }
    }
  `);

  function changeRecipeReaction(reaction: 'UPVOTE' | 'DOWNVOTE') {
    const isSameReaction = userReaction == reaction;
    const isNewRaction = userReaction == null && reaction != null;
    let actionToPerform = isSameReaction ? null : reaction;
    let newUpvoteCount = upvoteCount;
    let newDownvoteCount = downvoteCount;

    if (isSameReaction) {
      actionToPerform = null;
      newUpvoteCount = reaction == 'UPVOTE' ? upvoteCount - 1 : upvoteCount;
      newDownvoteCount = reaction == 'DOWNVOTE' ? downvoteCount - 1 : downvoteCount;
    } else if (isNewRaction) {
      newUpvoteCount = reaction == 'UPVOTE' ? upvoteCount + 1 : upvoteCount;
      newDownvoteCount = reaction == 'DOWNVOTE' ? downvoteCount + 1 : downvoteCount;
    } else {
      newUpvoteCount = reaction == 'UPVOTE' ? upvoteCount + 1 : upvoteCount - 1;
      newDownvoteCount = reaction == 'DOWNVOTE' ? downvoteCount + 1 : downvoteCount - 1;
    }

    updateRecipeReaction.mutate(
      { recipeId, reaction: actionToPerform },
      {
        optimisticResponse: {
          updateRecipeReaction: {
            id: recipeId,
            reactions: {
              downvoteCount: newDownvoteCount,
              upvoteCount: newUpvoteCount,
              userReaction: actionToPerform,
            },
          },
        },
      },
    );
  }
</script>

<div class="flex flex-col gap-2">
  <Typography variant="h3">Reactions</Typography>
  <div class="flex flex-row gap-3">
    <ReactionButton
      reactionType="like"
      count={upvoteCount}
      active={userReaction == 'UPVOTE'}
      on:click={() => changeRecipeReaction('UPVOTE')}
    />
    <ReactionButton
      reactionType="dislike"
      count={downvoteCount}
      active={userReaction == 'DOWNVOTE'}
      on:click={() => changeRecipeReaction('DOWNVOTE')}
    />
  </div>
</div>
