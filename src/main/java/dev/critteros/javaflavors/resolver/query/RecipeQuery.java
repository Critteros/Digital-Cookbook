package dev.critteros.javaflavors.resolver.query;

import dev.critteros.javaflavors.model.Recipe;
import dev.critteros.javaflavors.model.RecipeIngredient;
import dev.critteros.javaflavors.model.RecipeStep;
import dev.critteros.javaflavors.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@Transactional(readOnly = true)
public class RecipeQuery {
    final private RecipeRepository recipeRepository;

    public RecipeQuery(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @QueryMapping("recipes")
    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    @QueryMapping("recipeById")
    public Optional<Recipe> getRecipeById(@Argument String id) {
        try {
            UUID uid = UUID.fromString(id);
            return recipeRepository.findById(uid);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @QueryMapping("recipeByName")
    public List<Recipe> getRecipeByName(@Argument String name) {
        return recipeRepository.findByNameContainingIgnoreCase(name);
    }

    @SchemaMapping
    public List<RecipeIngredient> ingredients(Recipe recipe) {
        return recipe.getIngredients().stream().toList();
    }

    @SchemaMapping
    public List<RecipeStep> steps(Recipe recipe) {
        return recipe.getSteps().stream().toList();
    }
}
