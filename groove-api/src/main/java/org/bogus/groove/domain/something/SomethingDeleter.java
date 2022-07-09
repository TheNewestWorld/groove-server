package org.bogus.groove.domain.something;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.repository.SomethingRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SomethingDeleter {
    private final SomethingRepository somethingRepository;

    public void delete(Long somethingId) {
        somethingRepository.deleteById(somethingId);
    }
}
