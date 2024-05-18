package dev.critteros.javaflavors.resolver.mutation;

import dev.critteros.javaflavors.resolver.mutation.input.RecipeInput;
import dev.critteros.javaflavors.service.RecipeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import dev.critteros.javaflavors.model.Recipe;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional
public class RecipeMutation {

    private final RecipeService recipeService;

    public RecipeMutation(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @MutationMapping
    public Recipe createRecipe(
            @Argument(name = "recipe") RecipeInput recipeInput
    ) {
        return recipeService.createFromInput(recipeInput);
    }
}
