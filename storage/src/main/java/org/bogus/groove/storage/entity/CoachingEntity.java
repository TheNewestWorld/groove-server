package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bogus.groove.common.enumeration.CoachingStateType;

@Entity
@Table(name = "coaching")
@Getter
@NoArgsConstructor
public class CoachingEntity extends BaseEntity {

    @Column(name = "refTrainerId")
    private Long refTrainerId = 0L;

    @Column(name = "refUserId")
    private Long refUserId = 0L;

    @Setter
    @Column(name = "title")
    private String title;

    @Setter
    @Column(name = "content")
    private String content;

    @Setter
    @Column(name = "voiceFileMap")
    private String voiceFileMap;

    @Setter
    @Column(name = "imageFileMap")
    private String imageFileMap;

    @Setter
    @Column(name = "publicFlag")
    private boolean publicFlag;

    @Column(name = "coachingState")
    private CoachingStateType coachingState;

    public CoachingEntity(Long refTrainerId, Long refUserId, String title, String content, String voiceFileMap, String imageFileMap,
                          boolean publicFlag, CoachingStateType coachingState) {
        super();
        this.refTrainerId = refTrainerId;
        this.refUserId = refUserId;
        this.title = title;
        this.content = content;
        this.voiceFileMap = voiceFileMap;
        this.imageFileMap = imageFileMap;
        this.publicFlag = publicFlag;
        this.coachingState = coachingState;
    }

}