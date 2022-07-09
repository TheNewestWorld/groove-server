package org.bogus.groove.domain.something;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SomethingService {
    private final SomethingReader somethingReader;
    private final SomethingUpdater somethingUpdater;
    private final SomethingCreator somethingCreator;
    private final SomethingDeleter somethingDeleter;

    public Something get(Long somethingId) {
        return somethingReader.read(somethingId);
    }

    public Something create(String name, Integer age) {
        return somethingCreator.create(name, age);
    }

    public void updateAge(Long somethingId, Integer age) {
        somethingUpdater.updateAge(somethingId, age);
    }

    public void delete(Long somethingId) {
        somethingDeleter.delete(somethingId);
    }
}
