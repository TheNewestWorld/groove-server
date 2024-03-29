package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
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
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(name = "read_flag", nullable = false)
    private boolean readFlag;

    @Column(name = "delete_flag", nullable = false)
    private boolean deleteFlag;

    @Column(name = "link_url")
    private String linkUrl;

    @Column(name = "ref_send_user_id")
    private Long sendUserId;

    @Column(name = "ref_target_user_id")
    private Long targetUserId;

    public NotificationEntity(String content, NotificationType notificationType, String linkUrl, Long sendUserId, Long targetUserId) {
        super();
        this.content = content;
        this.notificationType = notificationType;
        this.linkUrl = linkUrl;
        this.sendUserId = sendUserId;
        this.targetUserId = targetUserId;
    }
}
