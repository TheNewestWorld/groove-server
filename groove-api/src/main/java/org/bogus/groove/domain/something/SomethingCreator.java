package org.bogus.groove.domain.something;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.SomethingEntity;
import org.bogus.groove.storage.repository.SomethingRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SomethingCreator {
    private final SomethingRepository somethingRepository;

    public Something create(String name, Integer age) {
        var entity = somethingRepository.save(new SomethingEntity(name, age));
        return new Something(
            entity.getId(),
            entity.getName(),
            entity.getAge()
        );
    }
}
