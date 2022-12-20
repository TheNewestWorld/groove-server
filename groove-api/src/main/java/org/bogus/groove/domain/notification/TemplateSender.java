package org.bogus.groove.domain.notification;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateSender {

    private final NotificationService notificationService;

    @Transactional
    public int noti(Long userId, TemplateSend dto) throws IOException {
        return notificationService.sendOne(userId, dto);
    }

    @Transactional
    @Async(value = "threadPoolTaskExecutor")
    public Future<Integer> notiAsync(Long userId, TemplateSend dto) throws IOException {
        return new AsyncResult<>(notificationService.send(userId, dto));
    }

    @Transactional
    public int notiGroup(List<Long> userList, TemplateSend dto) throws IOException {
        return notificationService.sendGroup(userList, dto);
    }

    @Transactional
    @Async(value = "threadPoolTaskExecutor")
    public Future<Integer> notiGroupAsync(List<Long> userList, TemplateSend dto) throws IOException {
        return new AsyncResult<>(notiGroup(userList, dto));
    }

}
