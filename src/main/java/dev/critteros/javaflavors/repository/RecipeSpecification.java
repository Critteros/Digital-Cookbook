package dev.critteros.javaflavors.repository;

import org.springframework.data.jpa.domain.Specification;

import dev.critteros.javaflavors.model.Recipe;

public class RecipeSpecification {

    public static Specification<Recipe> nameContains(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                "%" + name.toLowerCase() + "%");
    }

}
