package dev.critteros.javaflavors.resolver.query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.security.core.Authentication;

import dev.critteros.javaflavors.model.ReactionType;
import dev.critteros.javaflavors.model.Recipe;
import dev.critteros.javaflavors.model.RecipeIngredient;
import dev.critteros.javaflavors.model.RecipeStep;
import dev.critteros.javaflavors.model.UserProfile;
import dev.critteros.javaflavors.repository.RecipeReactionRepository;
import dev.critteros.javaflavors.repository.RecipeRepository;
import dev.critteros.javaflavors.repository.RecipeSpecification;
import dev.critteros.javaflavors.resolver.query.input.RecipeFilter;
import dev.critteros.javaflavors.service.UserProfileService;
import graphql.GraphQLException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@Transactional(readOnly = true)
public class RecipeQuery {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private RecipeReactionRepository recipeReactionRepository;

    @QueryMapping("recipes")
    public List<Recipe> getRecipes(@Argument RecipeFilter filter) {
        Specification<Recipe> spec = Specification.where(null);

        if (filter != null && filter.nameLike() != null) {
            spec = spec.and(RecipeSpecification.nameContains(filter.nameLike()));
        }

        return recipeRepository.findAll(spec);
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

    @SchemaMapping
    public List<RecipeIngredient> ingredients(Recipe recipe) {
        return recipe.getIngredients().stream().toList();
    }

    @SchemaMapping
    public List<RecipeStep> steps(Recipe recipe) {
        return recipe.getSteps().stream().toList();
    }

    @SchemaMapping
    public UserProfile author(Recipe recipe) {
        return recipe.getAuthor();
    }

    @SchemaMapping
    public Optional<String> image(Recipe recipe) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        var imageOption = recipe.getImage();
        if (imageOption.isEmpty()) {
            return Optional.empty();
        }
        var image = imageOption.get();
        return Optional.of(image.getResourceUrl(request));
    }

    @SchemaMapping
    public RecipeReactions reactions(Recipe recipe) {
        UUID recipeId = recipe.getId();
        Optional<UserProfile> userOption = userProfileService.getProfileForCurrentUser();

        ReactionType userReaction = null;
        if (userOption.isPresent()) {
            if (userOption.isEmpty()) {
                throw new GraphQLException("User not authenticated");
            }
            UserProfile user = userOption.get();

            var reaction = recipeReactionRepository.findByAuthorSubAndRecipeId(user.getSub(), recipeId);
            if (reaction.isPresent()) {
                userReaction = reaction.get().getReactionType();
            }
        }

        return new RecipeReactions(
                recipeReactionRepository.countByRecipeIdAndReactionType(recipeId, ReactionType.UPVOTE),
                recipeReactionRepository.countByRecipeIdAndReactionType(recipeId, ReactionType.DOWNVOTE),
                userReaction);
    }
}
