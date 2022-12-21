package org.bogus.groove.domain.voc;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.VocEntity;
import org.bogus.groove.storage.repository.VocRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VocCreator {
    private final VocRepository vocRepository;

    public Voc create(Long userId, String content) {
        var entity = vocRepository.save(new VocEntity(userId, content));
        return new Voc(entity);
    }
}
