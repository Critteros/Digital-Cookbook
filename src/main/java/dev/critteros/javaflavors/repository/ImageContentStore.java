package dev.critteros.javaflavors.repository;

import java.util.UUID;

import org.springframework.content.commons.store.ContentStore;

import dev.critteros.javaflavors.model.Image;

public interface ImageContentStore extends ContentStore<Image, UUID> {
}