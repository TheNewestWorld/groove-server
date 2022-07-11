package org.bogus.groove.domain.something;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.error.ErrorType;
import org.bogus.groove.storage.repository.SomethingRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SomethingUpdater {
    private final SomethingRepository somethingRepository;

    @Transactional
    public void updateAge(Long somethingId, Integer age) {
        var entity = somethingRepository.findById(somethingId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_SOMETHING));
        entity.setAge(age);
    }
}
