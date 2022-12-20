package org.bogus.groove.domain.notification;

import static org.junit.jupiter.api.Assertions.*;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.Password;
import org.bogus.groove.domain.user.User;
import org.bogus.groove.domain.user.UserRegister;
import org.bogus.groove.domain.user.UserRegisterParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class NotificationServiceTest {

    private final NotificationService notificationService;
    private final UserRegister userRegister;

    User user;
    String lastEventId = "";

    @BeforeEach
    public void init() {
        user = userRegister.register(new UserRegisterParam("test@test.com", new Password("test"), "test"));
    }

    @Test
    public void 알림구독() throws Exception {

        //when, then
        Assertions.assertDoesNotThrow(() -> notificationService.subscribe(user.getId(), lastEventId));
    }

    @Test
    public void 알림메시지전송() throws Exception {
        //given
        notificationService.subscribe(user.getId(), lastEventId);

        //when, then
        //Assertions.assertDoesNotThrow(() -> notificationService.send("댓글이 달렸습니다.", NotificationType.COMMENT, "localhost:8080/api/community/post/1/comment", user.getId()));

    }


}