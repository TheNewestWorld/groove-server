package org.bogus.groove.domain.coaching;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.common.enumeration.CoachingState;
import org.bogus.groove.common.enumeration.CoachingType;

@Getter
@AllArgsConstructor
@ToString
public class Coaching {
    private Long id;
    private Long refTrainerId;
    private Long refUserId;
    private String title;
    private String content;
    private CoachingType coachingType;
    private CoachingState coachingState;
    private int paymentMic;
    private boolean publicFlag;


}
