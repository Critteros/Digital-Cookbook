package dev.critteros.javaflavors.repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import dev.critteros.javaflavors.model.ReactionType;
import dev.critteros.javaflavors.model.RecipeReaction;

@Repository
public interface RecipeReactionRepository extends JpaRepository<RecipeReaction, UUID> {
    List<RecipeReaction> findByRecipeId(UUID recipeId);

    Optional<RecipeReaction> findByAuthorSubAndRecipeId(String authorId, UUID recipeId);

    Integer countByRecipeIdAndReactionType(UUID recipeId, ReactionType reactionType);

    void deleteById(@NonNull UUID id);
}
