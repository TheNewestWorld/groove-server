package org.bogus.groove.domain.notification;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.repository.NotificationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationReader {
    private final NotificationRepository notificationRepository;

    public Slice<Notification> readAllNotifications(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return notificationRepository.findAllNotifications(userId, pageable).map(Notification::new);
    }

}
