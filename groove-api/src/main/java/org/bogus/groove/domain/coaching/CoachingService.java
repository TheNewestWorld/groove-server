package org.bogus.groove.domain.coaching;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.CoachingStateType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoachingService {
    private final CoachingCreator coachingCreator;
    private final CoachingReader coachingReader;
    private final CoachingUpdater coachingUpdater;

    public Coaching createCoaching(Long refTrainerId, Long refUserId, String title, String content, String voiceFileMap,
                                   String imageFileMap, boolean publicFlag, CoachingStateType coachingState) {
        return coachingCreator.createCoaching(refTrainerId, refUserId, title, content, voiceFileMap, imageFileMap, publicFlag,
            coachingState);
    }
}
