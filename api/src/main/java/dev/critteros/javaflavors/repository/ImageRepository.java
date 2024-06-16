package dev.critteros.javaflavors.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import dev.critteros.javaflavors.model.Image;

@RepositoryRestResource(path = "images", collectionResourceRel = "images")
public interface ImageRepository extends JpaRepository<Image, UUID> {
    Image findByUid(UUID uid);
}