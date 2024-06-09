package dev.critteros.javaflavors.resolver.mutation;

import dev.critteros.javaflavors.resolver.mutation.dataclasses.RecipeDeleteResult;
import dev.critteros.javaflavors.resolver.mutation.input.RecipeInput;
import dev.critteros.javaflavors.service.RecipeService;
import dev.critteros.javaflavors.service.UserProfileService;
import graphql.GraphQLException;

import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.AccessDeniedException;
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

    @MutationMapping
    @PreAuthorize("isFullyAuthenticated()")
    public RecipeDeleteResult deleteRecipe(@Argument(name = "id") String id) {
        UUID uid = UUID.fromString(id);
        var userProfile = this.userProfileService.getProfileForCurrentUser();
        if (userProfile.isEmpty()) {
            throw new GraphQLException("User not authenticated");
        }

        if (!recipeService.recipeBelongsToUser(uid, userProfile.get())) {
            throw new AccessDeniedException("Recipe does not belong to this user");
        }

        recipeService.deleteRecipe(uid);

        return new RecipeDeleteResult(uid.toString());
    }
}
