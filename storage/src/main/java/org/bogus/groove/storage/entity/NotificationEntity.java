package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bogus.groove.common.enumeration.NotificationType;

@Entity
@Table(name = "notification")
@Getter
@NoArgsConstructor
public class NotificationEntity extends BaseEntity {

    @Column(name = "content")
    private String content;

    @Column(name = "notification_type")
    @Enumerated
    private NotificationType notificationType;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "ref_user_id")
    private Long userId;

    public NotificationEntity(String content, NotificationType notificationType, Long targetId, Long userId) {
        super();
        this.content = content;
        this.notificationType = notificationType;
        this.targetId = targetId;
        this.userId = userId;
    }
}
