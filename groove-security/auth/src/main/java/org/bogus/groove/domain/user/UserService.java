package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
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
    private final EmailAuthenticationReader emailAuthenticationReader;
    private final EmailAuthenticationRequester emailAuthenticationRequester;

    @Transactional
    public void register(UserRegisterParam param) {
        userRegister.register(param);
        emailAuthenticationRequester.request(param.getEmail());
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoFinder.find(userId);
    }

    public void updatePassword(String sessionKey, String password) {
        EmailAuthentication emailAuthentication = emailAuthenticationReader.read(sessionKey);

        if (emailAuthentication.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new NotFoundException(ErrorType.AUTHENTICATION_SESSION_EXPIRED);
        }

        userUpdater.update(emailAuthentication.getUserId(), password);
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
}
