package dev.critteros.javaflavors.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.critteros.javaflavors.model.Recipe;
import dev.critteros.javaflavors.repository.RecipeRespotiory;

@Service
public class RecipeService {
    final private RecipeRespotiory recipeRespotiory;

    public RecipeService(RecipeRespotiory recipeRespotiory) {
        this.recipeRespotiory = recipeRespotiory;
    }

    @Transactional(readOnly = true)
    public List<Recipe> getRecipes() {
        return recipeRespotiory.findAll();
    }

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRespotiory.save(recipe);
    }

    public Optional<Recipe> getRecipeById(UUID id) {
        return recipeRespotiory.findById(id);
    }

    public void deleteRecipeById(UUID id) {
        recipeRespotiory.deleteById(id);
    }

    public Recipe creatRecipe(String title, String description, Optional<String> image,
            Optional<Integer> preparationTimeMinutes,
            Optional<Integer> cookingTimeMinutes, Optional<Integer> totalTimeMinutes, Optional<Integer> servings) {
        Recipe recipe = new Recipe();
        recipe.setTitle(title);
        recipe.setDescription(description);
        image.ifPresent(recipe::setImage);
        preparationTimeMinutes.ifPresent(recipe::setPreparationTimeMinutes);
        cookingTimeMinutes.ifPresent(recipe::setCookingTimeMinutes);
        totalTimeMinutes.ifPresent(recipe::setTotalTimeMinutes);
        servings.ifPresent(recipe::setServings);
        return recipeRespotiory.save(recipe);
    }

    public Recipe createRecipe(String title, String description, List<String> ingredients, List<String> steps,
            Optional<String> image, Optional<Integer> preparationTimeMinutes, Optional<Integer> cookingTimeMinutes,
            Optional<Integer> totalTimeMinutes, Optional<Integer> servings) {
        Recipe recipe = new Recipe();
        recipe.setTitle(title);
        recipe.setDescription(description);
        recipe.setIngredients(ingredients);
        recipe.setSteps(steps);
        image.ifPresent(recipe::setImage);
        preparationTimeMinutes.ifPresent(recipe::setPreparationTimeMinutes);
        cookingTimeMinutes.ifPresent(recipe::setCookingTimeMinutes);
        totalTimeMinutes.ifPresent(recipe::setTotalTimeMinutes);
        servings.ifPresent(recipe::setServings);
        return recipeRespotiory.save(recipe);
    }

    public Recipe updateRecipe(UUID id, String title, String description, Optional<String> image) {
        Recipe recipe = recipeRespotiory.findById(id).orElseThrow();
        recipe.setTitle(title);
        recipe.setDescription(description);
        image.ifPresent(recipe::setImage);
        return recipeRespotiory.save(recipe);
    }

}
