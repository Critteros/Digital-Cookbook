package dev.critteros.javaflavors.resolver.mutation;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import dev.critteros.javaflavors.model.Recipe;
import dev.critteros.javaflavors.service.RecipeService;

@Controller
public class RecipeMutation {
    final private RecipeService recipeService;

    public RecipeMutation(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @MutationMapping
    public Recipe createRecipe(@Argument RecipeInput recipe) {

        return recipeService.createRecipe(
                recipe.title(),
                recipe.description(),
                recipe.ingredients(),
                recipe.steps(),
                recipe.image(),
                recipe.preparationTimeMinutes(),
                recipe.cookingTimeMinutes(),
                recipe.totalTimeMinutes(),
                recipe.servings());
    }
}
