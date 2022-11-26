package org.bogus.groove.mail.config;

import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.InternalServerException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleMailSender {

    private final JavaMailSender sender;
    private final SpringTemplateEn

    // TODO : 프론트 상의 후 링크 변경 필요
    private static final String CHANGE_PASSWORD_LINK = "http://localhost:8080";
    private static final String EMAIL_AUTHENTICATION_LINK = "http://localhost:8080";

    private MimeMessage getPasswordChangeMessage(String to, String sessionKey) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();

        try {
            message.addRecipients(MimeMessage.RecipientType.TO, to);
            message.setSubject("[Groove] 비밀번호 변경 요청");

            StringBuilder sb = new StringBuilder();
            sb.append("<h1>d안영 </ㅗ1>");
            sb.append(CHANGE_PASSWORD_LINK).append("/").append(sessionKey);

            message.setText(sb.toString(), "utf-8");
            message.setFrom(new InternetAddress("noreply@groove.com", "groove"));
        } catch (Exception e) {
            throw new InternalServerException(ErrorType.FAILED_TO_SEND_MAIL);
        }

        return message;
    }

    private MimeMessage emailAuthenticationMessage(String to, String sessionKey) {
        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setSubject("[Groove] 이메일 인증 요청");
            helper.setTo(to);

            Map<String, String> emailValues = new HashMap<>();
//            emailValues.put("")

            String html = templateEng

            message.addRecipients(MimeMessage.RecipientType.TO, to);
            message.setSubject("");

            StringBuilder sb = new StringBuilder();
            sb.append(EMAIL_AUTHENTICATION_LINK).append("/").append(sessionKey);
            message.setText(sb.toString(), "utf-8");
            message.setFrom(new InternetAddress("noreply@groove.com", "groove"));
        } catch (Exception e) {
            throw new InternalServerException(ErrorType.FAILED_TO_SEND_MAIL);
        }

        return message;
    }

    public void sendMessage(String to, String sessionKey, EmailType type) {
        MimeMessage message = null;

        if (EmailType.EMAIL_AUTHENTICATION.equals(type)) {
            message = emailAuthenticationMessage(to, sessionKey);
        } else if (EmailType.CHANGE_PASSWORD.equals(type)) {
            message = getPasswordChangeMessage(to, sessionKey);
        }

        sender.send(message);
    }
}
