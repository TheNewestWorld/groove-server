package org.bogus.groove.domain.coaching;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.storage.repository.CoachingRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoachingReader {
    private final CoachingRepository coachingRepository;

    public Coaching readCoaching(Long coachingId) {
        return readOrNull(coachingId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUNT_COACHING_INFO));
    }

    public Optional<Coaching> readOrNull(Long coachingId) {
        return coachingRepository.findById(coachingId).map(entity -> new Coaching(entity));
    }
}