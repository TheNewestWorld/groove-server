package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.EmailAuthenticationEntity;
import org.bogus.groove.storage.EmailAuthenticationRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailAuthenticationCreator {
    private final EmailAuthenticationRepository repository;

    public EmailAuthentication create(long userId) {
        var entity = repository.save(
            new EmailAuthenticationEntity(
                userId,
                UUID.randomUUID().toString(),
                LocalDateTime.now().plusDays(1)
            )
        );

        return new EmailAuthentication(entity);
    }
}
