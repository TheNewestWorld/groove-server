package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bogus.groove.common.enumeration.CoachingState;
import org.bogus.groove.common.enumeration.CoachingType;

@Entity
@Table(name = "coaching")
@Getter
@NoArgsConstructor
public class CoachingEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(name = "refTrainerUserId")
    @Setter // reader로 가져와야하니 setter?
    private Long refTrainerId;

    @Column(name = "refUserId")
    @Setter
    private Long refUserId;

    @Column(name = "title")
    @Setter
    private String title;

    @Column(name = "content")
    @Setter
    private String content;

    @Column(name = "voiceFileUrlMappingId") // mapping table
    @Setter
    private Long voiceFileUrlMappingId = 0L;

    @Column(name = "imageFileUrlMappingId") // mapping talbe
    @Setter
    private Long imageFileUrlMappingId = 0L;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @Setter
    private CoachingState coachingState;

    @Column(name = "paymentMic")
    @Setter
    private int paymentMic;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @Setter
    private CoachingType coachingType;

    @Column(name = "publicFlag")
    @Setter
    private boolean publicFlag;

}
