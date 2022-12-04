package org.bogus.groove.mail.config;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.InternalServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PasswordChangeEmailSender {
    private final JavaMailSender javaMailSender;

    @Value("${front-domain}")
    private String frontDomain;

    @Value("${reset-password-path}")
    private String resetPasswordPath;

    public void send(String to, String sessionKey) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.addRecipients(MimeMessage.RecipientType.TO, to);
            message.setSubject("[Groove] 비밀번호 변경 요청");

            StringBuilder sb = new StringBuilder();
            sb.append(frontDomain)
                .append(resetPasswordPath)
                .append(URLEncoder.encode(sessionKey, StandardCharsets.UTF_8));
            message.setText(sb.toString(), "utf-8");
            message.setFrom(new InternetAddress("noreply@groove.com", "groove"));
        } catch (Exception e) {
            throw new InternalServerException(ErrorType.FAILED_TO_SEND_MAIL);
        }

        javaMailSender.send(message);
    }
}
