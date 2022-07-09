package org.bogus.groove_auth.domain.user.authority;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.Authority;
import org.bogus.groove_auth.storage.UserAuthorityEntity;
import org.bogus.groove_auth.storage.UserAuthorityRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthorityUpdater {
    private final UserAuthorityRepository userAuthorityRepository;

    @Transactional
    public List<Authority> update(Long userId, List<Authority> authorities) {
        userAuthorityRepository.deleteAllByUserId(userId);
        userAuthorityRepository.saveAll(
            authorities.stream()
                .map((authority) -> new UserAuthorityEntity(userId, authority))
                .collect(Collectors.toList())
        );
        return authorities;
    }
}
