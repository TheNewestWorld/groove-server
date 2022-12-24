package org.bogus.groove.domain.notification;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.InternalServerException;
import org.bogus.groove.storage.entity.NotificationEntity;
import org.bogus.groove.storage.repository.NotificationRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationCreator {
    private final NotificationRepository notificationRepository;

    public Notification createNotification(Long sender, Long receiver, TemplateSend dto) {
        try {
            var entity =
                notificationRepository.save(new NotificationEntity(dto.getOutput(), dto.getNotificationType(), dto.getLinkUrl(), sender, receiver));
            return new Notification(entity);
        } catch (IllegalArgumentException e) {
            throw new InternalServerException(ErrorType.FAILED_TO_CREATE_NOTIFICATION);
        }
    }
}
