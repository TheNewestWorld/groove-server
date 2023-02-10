package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.MailSessionRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSessionReader {
    private final MailSessionRepository repository;

    public MailSession read(String sessionKey) {
        var entity = repository.findBySessionKey(sessionKey).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_SESSION));
        return new MailSession(entity);
    }
}
