package dev.critteros.javaflavors.resolver.mutation.input;

import dev.critteros.javaflavors.model.RecipeIngredient;
import dev.critteros.javaflavors.model.RecipeStep;

import java.util.List;
import java.util.Optional;

public record RecipeInput(
                String name,
                String description,
                List<RecipeIngredient> ingredients,
                List<RecipeStep> steps,
                Optional<Integer> preparationTimeMinutes,
                Optional<Integer> cookingTimeMinutes,
                Optional<Integer> totalTimeMinutes,
                Optional<Integer> servings

) {
}