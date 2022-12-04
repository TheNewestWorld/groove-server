package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.ProviderType;
import org.bogus.groove.storage.entity.EmailAuthenticationEntity;
import org.bogus.groove.storage.repository.EmailAuthenticationRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailAuthenticationCreator {
    private final EmailAuthenticationRepository repository;
    private final UserReader userReader;

    public EmailAuthentication create(long userId) {
        var entity = repository.save(
            new EmailAuthenticationEntity(
                userId,
                UUID.randomUUID().toString(),
                LocalDateTime.now().plusHours(1)
            )
        );
        return new EmailAuthentication(entity);
    }

    public EmailAuthentication create(String email) {
        User user = userReader.read(email, ProviderType.GROOVE);
        var entity = repository.save(
            new EmailAuthenticationEntity(
                user.getId(),
                UUID.randomUUID().toString(),
                LocalDateTime.now().plusHours(1)
            )
        );
        return new EmailAuthentication(entity);
    }
}
