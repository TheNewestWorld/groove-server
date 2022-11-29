package org.bogus.groove.config;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.UserType;
import org.bogus.groove.storage.repository.UserAuthorityRepository;
import org.bogus.groove.storage.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = userRepository.findByEmailAndTypeAndActiveIsTrue(username, UserType.GROOVE)
            .orElseThrow(() -> {
                throw new UsernameNotFoundException("존재하지 않는 유저입니다.");
            });
        var userAuthorityEntities = userAuthorityRepository.findAllByUserId(userEntity.getId());

        return new CustomUserDetails(userEntity, userAuthorityEntities);
    }
}
