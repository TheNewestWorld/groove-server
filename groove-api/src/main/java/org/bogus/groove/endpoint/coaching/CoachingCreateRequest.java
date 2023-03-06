package org.bogus.groove.endpoint.coaching;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bogus.groove.common.enumeration.CoachingStateType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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