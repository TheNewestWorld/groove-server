package org.bogus.groove.endpoint.notification;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.PageResponse;
import org.bogus.groove.config.GrooveUserDetails;
import org.bogus.groove.config.SecurityCode;
import org.bogus.groove.domain.notification.NotificationService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@Tag(name = "notification-controller")
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @Operation(summary = "알림 구독")
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    public SseEmitter subscribe(@AuthenticationPrincipal GrooveUserDetails userDetails,
                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        return notificationService.subscribe(userDetails.getUserId(), lastEventId);
    }

    @Operation(summary = "알림 리스트 조회")
    @Secured({SecurityCode.USER, SecurityCode.TRAINER, SecurityCode.ADMIN})
    @GetMapping(value = "/list")
    public CommonResponse<PageResponse<List<NotificationResponse>>> getNotificationList(
        @AuthenticationPrincipal GrooveUserDetails userDetails,
        @RequestParam int page,
        @RequestParam int size
    ) {
        var result = notificationService.getNotificationList(userDetails.getUserId(), page, size);
        return CommonResponse.success(
            new PageResponse<>(
                result.getNumber(),
                result.getSize(),
                result.map(NotificationResponse::new).toList(),
                result.hasNext()
            )
        );
    }
}
