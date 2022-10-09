package org.bogus.groove.domain.coaching;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.CoachingStateType;
import org.bogus.groove.storage.entity.CoachingEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoachingCreator {
    private final org.bogus.groove.storage.repository.CoachingRepository coachingRepository;

    public Coaching createCoaching(Long refTrainerId, Long refUserId, String title, String content, String voiceFileMap,
                                   String imageFileMap, boolean publicFlag, CoachingStateType coachingState) {
        var entity = coachingRepository.save(new CoachingEntity(refTrainerId, refUserId, title, content, voiceFileMap,
            imageFileMap, publicFlag, coachingState));
        return new Coaching(entity);
    }
}