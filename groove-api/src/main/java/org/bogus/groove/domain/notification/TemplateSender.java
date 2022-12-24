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
    public int noti(Long sendUserId, Long targetUserId, TemplateSend dto) throws IOException {
        return notificationService.sendOne(sendUserId, targetUserId, dto);
    }

    @Transactional
    @Async(value = "threadPoolTaskExecutor")
    public Future<Integer> notiAsync(Long sendUserId, Long targetUserId, TemplateSend dto) throws IOException {
        return new AsyncResult<>(notificationService.send(sendUserId, targetUserId, dto));
    }

    @Transactional
    public int notiGroup(Long sendUserId, List<Long> targetUserList, TemplateSend dto) throws IOException {
        return notificationService.sendGroup(sendUserId, targetUserList, dto);
    }

    @Transactional
    @Async(value = "threadPoolTaskExecutor")
    public Future<Integer> notiGroupAsync(Long sendUserId, List<Long> targetUserList, TemplateSend dto) throws IOException {
        return new AsyncResult<>(notiGroup(sendUserId, targetUserList, dto));
    }

}
