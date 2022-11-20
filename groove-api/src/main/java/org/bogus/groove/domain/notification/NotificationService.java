package org.bogus.groove.domain.notification;

import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.NotificationType;
import org.bogus.groove.storage.repository.EmitterRepository;
import org.bogus.groove.storage.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final EmitterRepository emitterRepository;
    private final NotificationCreator notificationCreator;


    public SseEmitter subscribe(Long userId, String lastEventId) {
        String emitterId = userId + "_" + System.currentTimeMillis();
        SseEmitter sseEmitter = emitterRepository.save(emitterId, new SseEmitter(timeout));
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

    public void send(String content, NotificationType notificationType, Long targetId, Long userId) {
        Notification notification = notificationCreator.createNotification(content, notificationType, targetId, userId);
        String receiverId = String.valueOf(userId);
        String eventId = userId + "_" + System.currentTimeMillis();
        Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByMemberId(receiverId);
        emitters.forEach(
            (key, emitter) -> {
                emitterRepository.saveEventCache(key, notification);
                sendNotification(emitter, eventId, key, No);
            }
        );
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

}
