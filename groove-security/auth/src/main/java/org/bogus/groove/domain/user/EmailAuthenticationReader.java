package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.EmailAuthenticationRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailAuthenticationReader {
    private final EmailAuthenticationRepository repository;

    public EmailAuthentication read(String sessionKey) {
        var entity = repository.findBySessionKey(sessionKey).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_SESSION));
        return new EmailAuthentication(entity);
    }
}
