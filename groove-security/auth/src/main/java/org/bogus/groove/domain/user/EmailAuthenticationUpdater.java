package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.storage.EmailAuthenticationEntity;
import org.bogus.groove.storage.EmailAuthenticationRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EmailAuthenticationUpdater {
    private final EmailAuthenticationRepository repository;

    @Transactional
    public EmailAuthentication update(String sessionKey, boolean isAuthenticated, LocalDateTime authenticatedAt) {
        EmailAuthenticationEntity entity = repository.findBySessionKey(sessionKey)
            .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_AUTHENTICATION_SESSION));

        entity.updateAuthentication(isAuthenticated, authenticatedAt);

        return new EmailAuthentication(entity);
    }

}
