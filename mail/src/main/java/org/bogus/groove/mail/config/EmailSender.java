package org.bogus.groove.mail.config;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender sender;

    // TODO GmailSender 로 변경
    public void sendAuthenticateRequest(String to, String sessionKey) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply@groove.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("축 당첨 500원!");
        simpleMailMessage.setText(String.format("http://어쩌구저쩌구/%s", sessionKey));

//        sender.send(simpleMailMessage);
    }
}
