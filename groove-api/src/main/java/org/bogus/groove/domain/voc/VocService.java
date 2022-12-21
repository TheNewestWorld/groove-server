package org.bogus.groove.domain.voc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VocService {
    private final VocCreator vocCreator;

    public VocGetResult create(Long userId, String content) {
        var entity = vocCreator.create(userId, content);
        return new VocGetResult(entity);
    }
}
