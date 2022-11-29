package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.enumeration.UserType;
import org.bogus.groove.domain.user.authority.UserAuthorityReader;
import org.bogus.groove.object_storage.Attachment;
import org.bogus.groove.object_storage.AttachmentReader;
import org.bogus.groove.util.JwtUtil;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoFinder {
    private final UserReader userReader;
    private final UserAuthorityReader userAuthorityReader;
    private final JwtUtil jwtUtil;
    private final AttachmentReader attachmentReader;

    public UserInfo find(String token) {
        var userId = jwtUtil.getUserIdByToken(token);
        return find(userId);
    }

    public UserInfo find(Long userId) {
        var user = userReader.read(userId);
        var authorities = userAuthorityReader.readAll(userId);
        var profileUri = getProfileUri(user);

        return new UserInfo(user.getId(), user.getEmail(), user.getType(), user.getNickname(), profileUri, authorities);
    }

    public UserInfo find(String email, UserType userType) {
        var user = userReader.read(email, userType);
        var authorities = userAuthorityReader.readAll(user.getId());
        var profileUri = getProfileUri(user);

        return new UserInfo(user.getId(), user.getEmail(), user.getType(), user.getNickname(), profileUri, authorities);
    }

    private String getProfileUri(User user) {
        return attachmentReader.readAll(user.getId(), AttachmentType.PROFILE)
            .stream().findFirst().map(Attachment::getUri).orElse(null);
    }
}
