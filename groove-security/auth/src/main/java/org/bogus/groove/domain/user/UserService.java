package org.bogus.groove.domain.user;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.mail.config.EmailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRegister userRegister;
    private final UserInfoFinder userInfoFinder;
    private final EmailSender emailSender;
    private final EmailAuthenticationCreator emailAuthenticationCreator;

    @Transactional
    public UserInfo register(UserRegisterParam param) {
        UserInfo user =  userRegister.register(param.getEmail(), param.getPassword(), param.getUserType());
        var emailAuthentication = emailAuthenticationCreator.create(user.getId());
        emailSender.sendAuthenticateRequest(user.getEmail(), emailAuthentication.getSessionKey());

        return user;
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoFinder.find(userId);
    }
}
