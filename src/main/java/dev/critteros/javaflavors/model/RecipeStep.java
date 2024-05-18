package dev.critteros.javaflavors.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recipe_step")
public class RecipeStep {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @NotBlank
    @NotNull
    private String stepName;

    @NotNull
    private Integer stepNumber;
}