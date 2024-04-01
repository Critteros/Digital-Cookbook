package dev.critteros.javaflavors.model;

import java.util.Optional;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private String id;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 100)
    private String title;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 1000)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @NotBlank
    @NotNull
    @Size(min = 1, max = 100)
    private Set<String> ingredients;

    @ElementCollection(fetch = FetchType.EAGER)
    @NotBlank
    @NotNull
    @Size(min = 1, max = 1000)
    private Set<String> steps;

    private String image;

    public Optional<String> getImage() {
        return Optional.ofNullable(image);
    }
}
