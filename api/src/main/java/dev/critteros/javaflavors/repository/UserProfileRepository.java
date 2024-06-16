package dev.critteros.javaflavors.repository;

import dev.critteros.javaflavors.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    UserProfile findBySub(String sub);
}
