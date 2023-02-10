package org.bogus.groove.domain.notification;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.client.user.UserClient;
import org.bogus.groove.client.user.UserInfo;
import org.bogus.groove.storage.repository.EmitterRepository;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final EmitterRepository emitterRepository;
    private final NotificationCreator notificationCreator;
    private final NotificationReader notificationReader;
    private final FreeMarkerService freeMarkerService;
    private final UserClient userClient;

    public SseEmitter subscribe(Long userId, String lastEventId) {
        String emitterId = makeTimeIncludeId(userId);
        SseEmitter sseEmitter = emitterRepository.save(emitterId, new SseEmitter(10 * 60 * 1000L));
        sseEmitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
        sseEmitter.onTimeout(() -> emitterRepository.deleteById(emitterId));

        //503에러 방지
        String eventId = userId + "_" + System.currentTimeMillis();
        sendNotification(sseEmitter, eventId, emitterId, "EventStream Created. [userId=" + userId + "]");

        //미수신한 Event 확인
        if (hasLostData(lastEventId)) {
            sendLostData(lastEventId, userId, emitterId, sseEmitter);
        }

        return sseEmitter;
    }

    private String makeTimeIncludeId(Long memberId) {
        return memberId + "_" + System.currentTimeMillis();
    }

    public int send(Long sender, Long receiver, TemplateSend dto) throws IOException {
        freeMarkerService.generateOutput(dto);
        Notification notification = saveNotification(sender, receiver, dto);
        String eventId = receiver + "_" + System.currentTimeMillis();
        Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByMemberId(String.valueOf(receiver));
        emitters.forEach(
            (key, emitter) -> {
                emitterRepository.saveEventCache(key, notification);
                sendNotification(emitter, eventId, key, notification);
            }
        );
        return 1;
    }

    private void sendNotification(SseEmitter sseEmitter, String eventId, String emitterId, Object data) {
        try {
            sseEmitter.send(SseEmitter.event().id(eventId).data(data));
        } catch (IOException exception) {
            emitterRepository.deleteById(emitterId);
        }
    }

    private boolean hasLostData(String lastEventId) {
        return !lastEventId.isEmpty();
    }

    private void sendLostData(String lastEventId, Long memberId, String emitterId, SseEmitter sseEmitter) {
        Map<String, Object> eventCaches = emitterRepository.findAllEventCacheStartWithByMemberId(String.valueOf(memberId));
        eventCaches.entrySet().stream().filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
            .forEach(entry -> sendNotification(sseEmitter, entry.getKey(), emitterId, entry.getValue()));
    }

    public Slice<NotificationGetResult> getNotificationList(Long userId, int page, int size) {
        var notifications = notificationReader.readAllNotifications(userId, page, size);
        return new SliceImpl<>(
            notifications.map(
                notification -> {
                    UserInfo userInfo = userClient.get(notification.getSendUserId());
                    return new NotificationGetResult(
                        notification,
                        userInfo.getProfileUri()
                    );
                }
            ).toList(),
            notifications.getPageable(),
            notifications.hasNext()
        );
    }

    @Transactional
    public int sendOne(Long sender, Long receiver, TemplateSend dto) throws IOException {
        freeMarkerService.generateOutput(dto);
        saveNotification(sender, receiver, dto);
        return 1;
    }

    @Transactional
    public int sendGroup(Long sender, List<Long> receiverList, TemplateSend dto) throws IOException {
        List<Long> receivers = receiverList.stream().distinct().collect(Collectors.toList());
        if (receivers.size() > 1) {
            dto.setAsGroupSend();
        }

        int result = 0;
        for (Long receiver : receivers) {
            result += sendOne(sender, receiver, dto);
        }
        return result;
    }

    @Transactional
    public Notification saveNotification(Long sender, Long receiver, TemplateSend dto) {
        return notificationCreator.createNotification(sender, receiver, dto);
    }

}
