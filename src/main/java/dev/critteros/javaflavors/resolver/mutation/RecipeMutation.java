package dev.critteros.javaflavors.resolver.mutation;

import dev.critteros.javaflavors.resolver.mutation.input.RecipeInput;
import dev.critteros.javaflavors.service.RecipeService;
import dev.critteros.javaflavors.service.UserProfileService;
import graphql.GraphQLException;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import dev.critteros.javaflavors.model.Recipe;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional
public class RecipeMutation {

    private final RecipeService recipeService;
    private UserProfileService userProfileService;

    public RecipeMutation(RecipeService recipeService, UserProfileService userProfileSerivce) {
        this.recipeService = recipeService;
        this.userProfileService = userProfileSerivce;
    }

    @MutationMapping
    @PreAuthorize("isFullyAuthenticated()")
    public Recipe createRecipe(
            @Argument(name = "recipe") RecipeInput recipeInput) {
        var userProfile = this.userProfileService.getProfileForCurrentUser();
        if (userProfile.isEmpty()) {
            throw new GraphQLException("User not authenticated");
        }
        var author = userProfile.get();
        return recipeService.createFromInput(recipeInput, author);
    }
}
