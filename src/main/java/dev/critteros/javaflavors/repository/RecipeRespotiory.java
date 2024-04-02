package dev.critteros.javaflavors.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.critteros.javaflavors.model.Recipe;

@Repository
public interface RecipeRespotiory extends JpaRepository<Recipe, UUID> {

}
