package dev.critteros.javaflavors.model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 100)
    private String title;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 1000)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(nullable = false, length = 1000)
    private List<String> ingredients;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(nullable = false, length = 1000)
    private List<@Valid String> steps;

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
