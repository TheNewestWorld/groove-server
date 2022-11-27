package org.bogus.groove.mail.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.InternalServerException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleMailSender {

    private final JavaMailSender sender;

    // TODO : 프론트 상의 후 링크 변경 필요
    private static final String CHANGE_PASSWORD_LINK = "http://localhost:8080";
    private static final String EMAIL_AUTHENTICATION_LINK = "http://localhost:8080";
    private static final String DOMAIN = "http://localhost:8080";


    private MimeMessage getPasswordChangeMessage(String to, String sessionKey) {
        MimeMessage message = sender.createMimeMessage();

        try {
            message.addRecipients(MimeMessage.RecipientType.TO, to);
            message.setSubject("[Groove] 비밀번호 변경 요청");

            StringBuilder sb = new StringBuilder();
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
            message.addRecipients(MimeMessage.RecipientType.TO, to);
            message.setSubject("[Groove] 이메일 인증 요청");

            String html = getAuthenticationHtml(sessionKey);

            message.setText(html, "utf-8", "html");
            message.setFrom(new InternetAddress("noreply@groove.com", "groove"));
        } catch (Exception e) {
            throw new InternalServerException(ErrorType.FAILED_TO_SEND_MAIL);
        }

        return message;
    }

    private String getAuthenticationHtml(String sessionKey) throws IOException {
        ClassPathResource resource = new ClassPathResource("mail-template.html");
        Path path = Paths.get(resource.getURI());
        List<String> content = Files.readAllLines(path);

        StringBuilder sb = new StringBuilder();
        content.forEach(sb::append);
        String html = sb.toString();
        html = html.replace("{{authenticationLink}}", getAuthenticationUrl(sessionKey));
        html = html.replace("{{logoUrl}}", getLogoUrl());
        html = html.replace("{{titleUrl}}", getTitleUrl());

        return html;
    }

    private String getAuthenticationUrl(String sessionKey) {
        return String.format("%s/%s", EMAIL_AUTHENTICATION_LINK, sessionKey);
    }

    private String getTitleUrl() {
        return DOMAIN + "/static/title.png";
    }

    private String getLogoUrl() {
        return DOMAIN + "/static/logo.png";
    }

    public void sendMessage(String to, String sessionKey, EmailType type) {
        MimeMessage message = null;

        if (EmailType.EMAIL_AUTHENTICATION.equals(type)) {
            message = emailAuthenticationMessage(to, sessionKey);
        } else if (EmailType.CHANGE_PASSWORD.equals(type)) {
            message = getPasswordChangeMessage(to, sessionKey);
        } else {
            throw new InternalServerException(ErrorType.NOT_SUPPORTED);
        }

        sender.send(message);
    }
}
