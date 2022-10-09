package org.bogus.groove.endpoint.coaching;

import lombok.Getter;
import org.bogus.groove.common.enumeration.CoachingStateType;
import org.bogus.groove.domain.coaching.Coaching;

@Getter
public class CoachingResponse {
    private Long id;
    private Long refTrainerId;
    private Long refUserId;
    private String title;
    private String content;
    private String voiceFileMap;
    private String imageFileMap;
    private boolean publicFlag;
    private CoachingStateType coachingState;

    public CoachingResponse(Coaching coaching) {
        this.id = coaching.getId();
        this.refTrainerId = coaching.getRefTrainerId();
        this.refUserId = coaching.getRefUserId();
        this.title = coaching.getTitle();
        this.content = coaching.getContent();
        this.voiceFileMap = coaching.getVoiceFileMap();
        this.imageFileMap = coaching.getImageFileMap();
        this.publicFlag = coaching.isPublicFlag();
        this.coachingState = coaching.getCoachingState();
    }
}
