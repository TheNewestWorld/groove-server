package org.bogus.groove.domain.something;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.error.AppException;
import org.bogus.groove.error.ErrorType;
import org.bogus.groove.storage.repository.SomethingRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SomethingReader {
    private final SomethingRepository somethingRepository;

    public Something read(Long somethingId) {
        return readOrNull(somethingId).orElseThrow(() -> new AppException(ErrorType.NOT_FOUND_SOMETHING));
    }

    public Optional<Something> readOrNull(Long somethingId) {
        return somethingRepository.findById(somethingId).map(entity -> new Something(entity.getId(), entity.getName(), entity.getAge()));
    }
}
