package dev.critteros.javaflavors.resolver.mutation.input;

public record RecipeIngredientInput(
        String name,
        Float quantity,
        String unit
) {
}
