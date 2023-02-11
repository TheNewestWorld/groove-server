package org.bogus.groove.mail.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.InternalServerException;
import org.bogus.groove.object_storage.ObjectUriMaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleMailSender {

    private final JavaMailSender sender;
    private final ObjectUriMaker objectUriMaker;

    @Value("${front-domain}")
    private String frontDomain;

    @Value("${email-authentication-path}")
    private String emailAuthenticationPath;

    @Value("${reset-password-path}")
    private String resetPasswordPath;

    @Value("classpath:authentication-template.htm")
    private Resource emailAuthenticationTemplate;

    public void sendMessage(String to, String sessionKey, EmailType type) {
        if (EmailType.EMAIL_AUTHENTICATION.equals(type)) {
            sender.send(emailAuthenticationMessage(to, sessionKey));
        } else if (EmailType.CHANGE_PASSWORD.equals(type)) {
            sender.send(getPasswordChangeMessage(to, sessionKey));
        } else {
            throw new InternalServerException(ErrorType.NOT_SUPPORTED);
        }
    }

    private MimeMessage getPasswordChangeMessage(String to, String sessionKey) {
        MimeMessage message = sender.createMimeMessage();

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
            throw new InternalServerException(ErrorType.FAILED_TO_SEND_MAIL, e);
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
            throw new InternalServerException(ErrorType.FAILED_TO_SEND_MAIL, e);
        }

        return message;
    }

    private String getAuthenticationHtml(String sessionKey) throws IOException {
        ClassPathResource resource = new ClassPathResource("authentication-template.htm");

        var html = FileCopyUtils.copyToString(new BufferedReader(new InputStreamReader(resource.getInputStream())));
        html = html.replace("{{authenticationLink}}", getAuthenticationUrl(sessionKey));
        html = html.replace("{{logoUrl}}", getLogoUrl());

        return html;
    }

    private String getAuthenticationUrl(String sessionKey) {
        StringBuilder sb = new StringBuilder();

        sb.append(frontDomain)
            .append(emailAuthenticationPath)
            .append(URLEncoder.encode(sessionKey, StandardCharsets.UTF_8));

        return sb.toString();
    }

    private String getLogoUrl() {
        return objectUriMaker.make(AttachmentType.MISCELLANEOUS, "logo.png");
    }
}
