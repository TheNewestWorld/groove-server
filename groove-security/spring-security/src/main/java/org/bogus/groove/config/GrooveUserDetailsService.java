package org.bogus.groove.config;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.ProviderType;
import org.bogus.groove.storage.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrooveUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = userRepository.findByEmailAndProviderTypeAndActiveIsTrue(username, ProviderType.GROOVE)
            .orElseThrow(() -> {
                throw new UsernameNotFoundException("존재하지 않는 유저입니다.");
            });

        return new GrooveUserDetails(
            userEntity.getId(),
            userEntity.getEmail(),
            userEntity.getPassword(),
            userEntity.getProviderType(),
            userEntity.getRole()
        );
    }
}
