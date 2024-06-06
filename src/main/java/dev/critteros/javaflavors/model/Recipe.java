package dev.critteros.javaflavors.model;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "author_id", nullable = true)
    private UserProfile author;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 1000)
    private String description;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<RecipeIngredient> ingredients;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<RecipeStep> steps;

    @Nullable
    private String image;

    public Optional<String> getImage() {
        return Optional.ofNullable(image);
    }

    @Nullable
    private Integer preparationTimeMinutes;

    public Optional<Integer> getPreparationTimeMinutes() {
        return Optional.ofNullable(preparationTimeMinutes);
    }

    @Nullable
    private Integer servings;

    public Optional<Integer> getServings() {
        return Optional.ofNullable(servings);
    }

    @Nullable
    private Integer cookingTimeMinutes;

    public Optional<Integer> getCookingTimeMinutes() {
        return Optional.ofNullable(cookingTimeMinutes);
    }

    @Nullable
    private Integer totalTimeMinutes;

    public Optional<Integer> getTotalTimeMinutes() {
        return Optional.ofNullable(totalTimeMinutes);
    }
}
