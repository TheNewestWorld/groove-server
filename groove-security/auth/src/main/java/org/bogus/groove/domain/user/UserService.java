package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.Password;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.enumeration.ProviderType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.domain.user.token.TokenValidator;
import org.bogus.groove.mail.config.EmailType;
import org.bogus.groove.mail.config.GoogleMailSender;
import org.bogus.groove.object_storage.AttachmentDeleter;
import org.bogus.groove.object_storage.AttachmentReader;
import org.bogus.groove.object_storage.AttachmentUploadParam;
import org.bogus.groove.object_storage.AttachmentUploader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRegister userRegister;
    private final UserUpdater userUpdater;
    private final UserInfoFinder userInfoFinder;
    private final AttachmentUploader attachmentUploader;
    private final AttachmentReader attachmentReader;
    private final AttachmentDeleter attachmentDeleter;
    private final MailSessionCreator mailSessionCreator;
    private final MailSessionReader mailSessionReader;
    private final TokenValidator tokenValidator;
    private final GoogleMailSender googleMailSender;

    @Transactional
    public void register(UserRegisterParam param) {
        User user = userRegister.register(param);
        sendAuthenticationMail(user.getEmail());
    }

    @Transactional
    public void sendAuthenticationMail(String email) {
        UserInfo user = userInfoFinder.find(email, ProviderType.GROOVE);
        var session = mailSessionCreator.create(user.getId());
        googleMailSender.sendMessage(email, session.getSessionKey(), EmailType.EMAIL_AUTHENTICATION);
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoFinder.find(userId);
    }

    @Transactional
    public void sendPasswordUpdateLink(String email) {
        UserInfo user = userInfoFinder.find(email, ProviderType.GROOVE);
        var session = mailSessionCreator.create(user.getId());
        googleMailSender.sendMessage(email, session.getSessionKey(), EmailType.CHANGE_PASSWORD);
    }

    public void updatePassword(String sessionKey, Password password) {
        MailSession mailSession = mailSessionReader.read(sessionKey);

        if (mailSession.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new NotFoundException(ErrorType.AUTHENTICATION_SESSION_EXPIRED);
        }

        userUpdater.update(mailSession.getUserId(), password);
    }

    public void updateNickname(long userId, String nickname) {
        userUpdater.update(userId, nickname);
    }

    @Transactional
    public void updateProfile(long userId, UserProfileUpdateParam param) {
        var profiles = attachmentReader.readAll(userId, AttachmentType.PROFILE);
        profiles.forEach((attachment -> attachmentDeleter.delete(attachment.getId())));

        attachmentUploader.upload(
            new AttachmentUploadParam(
                param.getInputStream(),
                param.getFileName(),
                param.getSize(),
                userId,
                AttachmentType.PROFILE
            )
        );
    }

    @Transactional
    public void unregister(Long userId, String accessToken) {
        userUpdater.inactivate(userId);
        tokenValidator.invalidate(accessToken);
    }

    @Transactional
    public void removeProfile(Long userId) {
        var profiles = attachmentReader.readAll(userId, AttachmentType.PROFILE);
        profiles.forEach((attachment -> attachmentDeleter.delete(attachment.getId())));
    }
}
