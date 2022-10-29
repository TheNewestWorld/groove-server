package org.bogus.groove.domain.user;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    public UserInfo register(UserRegisterParam param) {
        UserInfo user =  userRegister.register(param.getEmail(), param.getPassword(), param.getUserType());
        EmailAuthentication emailAuthentication = emailAuthenticationCreator.create(user.getId());
        googleMailSender.sendMessage(user.getEmail(), emailAuthentication.getSessionKey(), EmailType.EMAIL_AUTHENTICATION);

        return user;
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoFinder.find(userId);
    }

    @Transactional
    public void sendPasswordUpdateLink(String email) {
        UserInfo user = userInfoFinder.find(email, UserType.GROOVE);

        EmailAuthentication emailAuthentication = emailAuthenticationCreator.create(user.getId());
        googleMailSender.sendMessage(user.getEmail(), emailAuthentication.getSessionKey(), EmailType.CHANGE_PASSWORD);
    }

    public void updatePassword(String sessionKey, String password) {
        EmailAuthentication emailAuthentication = emailAuthenticationReader.read(sessionKey);
        userUpdater.update(emailAuthentication.getUserId(), password);
    }
}
