package org.bogus.groove.domain.notification;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.NotificationType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.InternalServerException;
import org.bogus.groove.storage.entity.NotificationEntity;
import org.bogus.groove.storage.repository.NotificationRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationCreator {
    private final NotificationRepository notificationRepository;

    public Notification createNotification(String content, NotificationType notificationType, Long targetId, Long userId) {
        try {
            var entity = notificationRepository.save(new NotificationEntity(content, notificationType, targetId, userId));
            return new Notification(entity);
        } catch (IllegalArgumentException e) {
            throw new InternalServerException(ErrorType.FAILED_TO_CREATE_NOTIFICATION);
        }
    }
}
