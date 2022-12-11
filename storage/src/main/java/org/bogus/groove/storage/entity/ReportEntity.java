package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bogus.groove.common.enumeration.ReportReasonType;
import org.bogus.groove.common.enumeration.ReportTargetType;

@Entity
@Table(name = "report")
@Getter
@NoArgsConstructor
public class ReportEntity extends BaseEntity {

    @Column(name = "ref_user_id")
    private Long userId;

    @Column(name = "ref_target_id")
    private Long targetId;

    @Column(name = "target_type")
    private ReportTargetType reportTargetType;

    @Column(name = "reason_type")
    private ReportReasonType reportReasonType;

    public ReportEntity(Long userId, Long targetId, ReportTargetType reportTargetType,
                        ReportReasonType reportReasonType) {
        this.userId = userId;
        this.targetId = targetId;
        this.reportTargetType = reportTargetType;
        this.reportReasonType = reportReasonType;
    }
}
