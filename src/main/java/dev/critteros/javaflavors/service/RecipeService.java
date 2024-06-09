package dev.critteros.javaflavors.service;

import dev.critteros.javaflavors.model.Recipe;
import dev.critteros.javaflavors.model.RecipeIngredient;
import dev.critteros.javaflavors.model.RecipeStep;
import dev.critteros.javaflavors.model.UserProfile;
import dev.critteros.javaflavors.repository.RecipeRepository;
import dev.critteros.javaflavors.resolver.mutation.input.RecipeInput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;

    RecipeService(RecipeRepository recipeRepository, ModelMapper modelMapper) {
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
    }

    public Recipe createFromInput(RecipeInput input, UserProfile author) {
        Recipe recipe = modelMapper.map(input, Recipe.class);

        var ingredients = input.ingredients().stream().map((ing) -> {
            var ingredient = modelMapper.map(ing, RecipeIngredient.class);
            ingredient.setRecipe(recipe);
            return ingredient;
        }).collect(Collectors.toSet());
        recipe.setIngredients(ingredients);

        var steps = input.steps().stream().map((step) -> {
            var recipeStep = modelMapper.map(step, RecipeStep.class);
            recipeStep.setRecipe(recipe);
            return recipeStep;
        }).collect(Collectors.toSet());
        recipe.setSteps(steps);
        recipe.setAuthor(author);

        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(UUID id) {
        recipeRepository.deleteById(id);
    }

    public boolean recipeBelongsToUser(UUID id, UserProfile userProfile) {
        return recipeRepository.findById(id)
                .map(recipe -> {
                    if (recipe.getAuthor() != null) {
                        return recipe.getAuthor().getSub().equals(userProfile.getSub());
                    } else {
                        return false;
                    }
                })
                .orElse(false);
    }

}
