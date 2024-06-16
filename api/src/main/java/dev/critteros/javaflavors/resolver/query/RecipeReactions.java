package dev.critteros.javaflavors.resolver.query;

import org.springframework.lang.Nullable;

import dev.critteros.javaflavors.model.ReactionType;

public record RecipeReactions(Integer upvoteCount, Integer downvoteCount, @Nullable ReactionType userReaction) {
}
