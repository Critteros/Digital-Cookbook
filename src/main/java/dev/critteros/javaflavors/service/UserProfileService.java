package dev.critteros.javaflavors.service;

import dev.critteros.javaflavors.model.UserProfile;
import dev.critteros.javaflavors.repository.UserProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final ModelMapper modelMapper;

    UserProfileService(UserProfileRepository userProfileRepository, ModelMapper modelMapper) {
        this.userProfileRepository = userProfileRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<UserInfo> getCurrentUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication.getPrincipal() instanceof Jwt jwt)) {
            return Optional.empty();
        }

        String email = jwt.getClaimAsString("email");
        String sub = jwt.getClaimAsString("sub");
        String name = jwt.getClaimAsString("name");
        String nickname = jwt.getClaimAsString("nickname");
        String preferredUsername = jwt.getClaimAsString("preferred_username");


        return Optional.of(UserInfo.builder()
                .email(email)
                .sub(sub)
                .name(name)
                .nickname(nickname)
                .preferredUsername(preferredUsername)
                .build());
    }

    public Optional<UserProfile> profileForSub(String sub) {
        var profile = userProfileRepository.findBySub(sub);
        return Optional.ofNullable(profile);
    }

    public UserProfile createProfileFromInfo(UserInfo userInfo) {
        var profile = modelMapper.map(userInfo, UserProfile.class);

        return userProfileRepository.save(profile);
    }

    public Optional<UserProfile> getProfileForCurrentUser() {
        var userInfoOptional = getCurrentUserInfo();
        if (userInfoOptional.isEmpty()) {
            return Optional.empty();
        }
        var userInfo = userInfoOptional.get();

        UserProfile profile = profileForSub(userInfo.sub()).orElseGet(() -> createProfileFromInfo(userInfo));

        profile.setEmail(userInfo.email());
        profile.setName(userInfo.name());
        profile.setNickname(userInfo.nickname());
        profile.setPreferredUsername(userInfo.preferredUsername());

        return Optional.of(userProfileRepository.save(profile));
    }

}
