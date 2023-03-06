package org.bogus.groove.domain.coaching;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.repository.CoachingRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoachingDeleter {
    private final CoachingRepository coachingRepository;

    @Transactional
    public void deleteCoaching(Long coachingId) {
    }
}
