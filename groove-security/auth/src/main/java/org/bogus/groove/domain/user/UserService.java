package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.mail.config.EmailType;
import org.bogus.groove.mail.config.GoogleMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRegister userRegister;
    private final UserUpdater userUpdater;
    private final UserInfoFinder userInfoFinder;
    private final EmailAuthenticationCreator emailAuthenticationCreator;
    private final EmailAuthenticationReader emailAuthenticationReader;
    private final GoogleMailSender googleMailSender;

    @Transactional
    public User register(UserRegisterParam param) {
        User user = userRegister.register(param);
        sendAuthenticationMail(user.getEmail());

        return user;
    }

    public void sendAuthenticationMail(String email) {
        UserInfo user = userInfoFinder.find(email, UserType.GROOVE);
        emailAuthenticationCreator.create(user.getId(), user.getEmail(), EmailType.EMAIL_AUTHENTICATION);
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoFinder.find(userId);
    }

    public void sendPasswordUpdateLink(String email) {
        UserInfo user = userInfoFinder.find(email, UserType.GROOVE);
        emailAuthenticationCreator.create(user.getId(), user.getEmail(), EmailType.CHANGE_PASSWORD);
    }

    public void updatePassword(String sessionKey, String password) {
        EmailAuthentication emailAuthentication = emailAuthenticationReader.read(sessionKey);

        if (emailAuthentication.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new NotFoundException(ErrorType.AUTHENTICATION_SESSION_EXPIRED);
        }

        userUpdater.update(emailAuthentication.getUserId(), password);
    }
}
