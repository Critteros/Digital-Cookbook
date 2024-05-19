package dev.critteros.javaflavors.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_profile")
public class UserProfile {
    @Id
    private String sub;

    @NotNull
    private String preferredUsername;

    @NotNull
    private String nickname;

    @NotNull
    private String name;

    @NotNull
    private String email;
}
