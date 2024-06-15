package dev.critteros.javaflavors.resolver.mutation;

import dev.critteros.javaflavors.resolver.mutation.dataclasses.RecipeDeleteResult;
import dev.critteros.javaflavors.resolver.mutation.input.RecipeInput;
import dev.critteros.javaflavors.service.RecipeService;
import dev.critteros.javaflavors.service.UserProfileService;
import graphql.GraphQLException;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import dev.critteros.javaflavors.model.ReactionType;
import dev.critteros.javaflavors.model.Recipe;
import dev.critteros.javaflavors.model.RecipeReaction;
import dev.critteros.javaflavors.model.UserProfile;
import dev.critteros.javaflavors.repository.RecipeReactionRepository;
import dev.critteros.javaflavors.repository.RecipeRepository;

import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional
public class RecipeMutation {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private RecipeReactionRepository recipeReactionRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @MutationMapping
    @PreAuthorize("isFullyAuthenticated()")
    public Recipe createRecipe(
            @Argument(name = "recipe") RecipeInput recipeInput, @Argument(name = "imageUid") String imageUid) {
        var userProfile = this.userProfileService.getProfileForCurrentUser();
        if (userProfile.isEmpty()) {
            throw new GraphQLException("User not authenticated");
        }
        var author = userProfile.get();
        return recipeService.createFromInput(recipeInput, author, imageUid);
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

    @MutationMapping
    @PreAuthorize("isFullyAuthenticated()")
    public Recipe updateRecipeReaction(@Argument(name = "recipeId") String id,
            @Argument(name = "reaction") @Nullable ReactionType reaction) {
        UUID recipeId = UUID.fromString(id);
        Optional<UserProfile> userProfileOption = this.userProfileService.getProfileForCurrentUser();
        if (userProfileOption.isEmpty()) {
            throw new GraphQLException("User not authenticated");
        }
        UserProfile userProfile = userProfileOption.get();

        Optional<RecipeReaction> reactionOption = recipeReactionRepository.findByAuthorSubAndRecipeId(
                userProfile.getSub(),
                recipeId);

        if (reaction == null) {
            if (reactionOption.isPresent()) {
                recipeReactionRepository.deleteById(reactionOption.get().getUid());
            }
            return recipeRepository.findById(recipeId).orElseThrow();
        }

        if (reactionOption.isPresent()) {
            RecipeReaction reactionEntity = reactionOption.get();
            reactionEntity.setReactionType(reaction);
            recipeReactionRepository.save(reactionEntity);
        } else {
            RecipeReaction reactionEntity = new RecipeReaction();
            reactionEntity.setAuthor(userProfile);
            reactionEntity.setRecipe(recipeRepository.findById(recipeId).orElseThrow());
            reactionEntity.setReactionType(reaction);
            recipeReactionRepository.save(reactionEntity);
        }

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow();
        return recipe;
    }
}
