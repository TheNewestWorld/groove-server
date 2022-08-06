package org.bogus.groove_auth.domain.user.authority;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove_auth.storage.UserAuthorityEntity;
import org.bogus.groove_auth.storage.UserAuthorityRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthorityReader {
    private final UserAuthorityRepository userAuthorityRepository;

    public List<Authority> readAll(Long userId) {
        return userAuthorityRepository.findAllByUserId(userId).stream()
            .map(UserAuthorityEntity::getAuthority)
            .collect(Collectors.toList());
    }
}
