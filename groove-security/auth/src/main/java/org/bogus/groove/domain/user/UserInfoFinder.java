package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.enumeration.ProviderType;
import org.bogus.groove.object_storage.Attachment;
import org.bogus.groove.object_storage.AttachmentReader;
import org.bogus.groove.util.JwtUtil;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoFinder {
    private final UserReader userReader;
    private final JwtUtil jwtUtil;
    private final AttachmentReader attachmentReader;

    public UserInfo find(String token) {
        var userId = jwtUtil.getUserIdByToken(token);
        return find(userId);
    }

    public UserInfo find(Long userId) {
        var user = userReader.read(userId);
        var profileUri = getProfileUri(user);

        return new UserInfo(user.getId(), user.getEmail(), user.getProviderType(), user.getNickname(), profileUri, user.getRole());
    }

    public UserInfo find(String email, ProviderType providerType) {
        var user = userReader.read(email, providerType);
        var profileUri = getProfileUri(user);

        return new UserInfo(user.getId(), user.getEmail(), user.getProviderType(), user.getNickname(), profileUri, user.getRole());
    }

    private String getProfileUri(User user) {
        return attachmentReader.readAll(user.getId(), AttachmentType.PROFILE)
            .stream().findFirst().map(Attachment::getUri).orElse(null);
    }
}
