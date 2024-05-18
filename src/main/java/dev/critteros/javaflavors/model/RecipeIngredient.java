package dev.critteros.javaflavors.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredient {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    private Double quantity;

    @Nullable
    private String unit;

    public Optional<String> getUnit() {
        return Optional.ofNullable(unit);
    }

}