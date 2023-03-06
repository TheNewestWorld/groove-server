package org.bogus.groove.domain.coaching;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.common.enumeration.CoachingStateType;
import org.bogus.groove.storage.entity.CoachingEntity;

@Getter
@AllArgsConstructor
@ToString
public class Coaching {
    private Long id;
    private Long refTrainerId;
    private Long refUserId;
    private String title;
    private String content;
    private String voiceFileMap;
    private String imageFileMap;
    private boolean publicFlag;
    private CoachingStateType coachingState;
    private LocalDateTime createAt;

    public Coaching(CoachingEntity entity) {
        this.id = entity.getId();
        this.refTrainerId = entity.getRefTrainerId();
        this.refUserId = entity.getRefUserId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.voiceFileMap = entity.getVoiceFileMap();
        this.imageFileMap = entity.getVoiceFileMap();
        this.publicFlag = entity.isPublicFlag();
        this.coachingState = entity.getCoachingState();
        this.createAt = entity.getCreatedAt();
    }
}