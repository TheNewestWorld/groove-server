package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.entity.EmailAuthenticationEntity;
import org.bogus.groove.storage.repository.EmailAuthenticationRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EmailAuthenticationUpdater {
    private final EmailAuthenticationRepository repository;

    @Transactional
    public EmailAuthentication update(String sessionKey, boolean verified, LocalDateTime verifiedAt) {
        EmailAuthenticationEntity entity = repository.findBySessionKey(sessionKey)
            .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_SESSION));

        entity.updateAuthentication(verified, verifiedAt);

        return new EmailAuthentication(entity);
    }

}
