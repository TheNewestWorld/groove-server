package org.bogus.groove.endpoint.coaching;

import lombok.Getter;
import lombok.Setter;
import org.bogus.groove.common.enumeration.CoachingStateType;

@Getter
@Setter
public class CoachingCreateRequest {
    private Long refTrainerId;
    private Long refUserId;
    private String title;
    private String content;
    private String voiceFileMap;
    private String imageFileMap;
    private boolean publicFlag;
    private CoachingStateType coachingState;

}