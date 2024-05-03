package dev.critteros.javaflavors.resolver.query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import dev.critteros.javaflavors.model.Recipe;
import dev.critteros.javaflavors.service.RecipeService;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional(readOnly = true)
public class RecipeQuery {
    final private RecipeService recipeService;

    public RecipeQuery(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @QueryMapping("recipes")
    @PreAuthorize("isFullyAuthenticated()")
    public List<Recipe> getRecipes() {
        return recipeService.getRecipes();
    }

    @QueryMapping("recipeById")
    @SuppressWarnings("null")
    public Optional<Recipe> getRecipeById(@Argument String id) {
        UUID uid = UUID.fromString(id);
        return recipeService.getRecipeById(uid);
    }
}
