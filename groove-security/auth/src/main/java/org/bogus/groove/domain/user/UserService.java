package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.object_storage.AttachmentUploadParam;
import org.bogus.groove.object_storage.AttachmentUploader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRegister userRegister;
    private final UserInfoFinder userInfoFinder;
    private final UserUpdater userUpdater;
    private final AttachmentUploader attachmentUploader;

    public void register(UserRegisterParam param) {
        userRegister.register(param);
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoFinder.find(userId);
    }

    public void updateNickname(long userId, String nickname) {
        userUpdater.update(userId, nickname);
    }

    public void updateProfile(long userId, UserProfileUpdateParam param) {
        var result = attachmentUploader.upload(
            new AttachmentUploadParam(
                param.getInputStream(),
                param.getFileName(),
                param.getSize(),
                AttachmentType.PROFILE
            )
        );
        userUpdater.update(userId, result.getAttachmentId());
    }
}
