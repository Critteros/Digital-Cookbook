package dev.critteros.javaflavors.resolver.mutation;

import java.util.List;
import java.util.Optional;

public record RecipeInput(String title, String description, List<String> ingredients, List<String> steps,
        Optional<String> image,
        Optional<Integer> preparationTimeMinutes,
        Optional<Integer> cookingTimeMinutes,
        Optional<Integer> totalTimeMinutes,
        Optional<Integer> servings

) {
}