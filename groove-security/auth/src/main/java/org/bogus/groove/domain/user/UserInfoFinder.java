package org.bogus.groove.domain.user;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
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
        var profileUri = attachmentReader.readOrNull(user.getProfileId()).map(Attachment::getUri).orElse(null);

        return new UserInfo(user.getId(), user.getEmail(), user.getType(), profileUri, authorities);
    }

    public UserInfo find(String email, UserType userType) {
        var user = userReader.readOrNull(email, userType).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));
        var authorities = userAuthorityReader.readAll(user.getId());
        var profileUri = attachmentReader.readOrNull(user.getProfileId()).map(Attachment::getUri).orElse(null);

        return new UserInfo(user.getId(), user.getEmail(), user.getType(), profileUri, authorities);
    }

    public Optional<UserInfo> findOrNull(String email, UserType userType) {
        try {
            return Optional.of(find(email, userType));
        } catch (Throwable throwable) {
            return Optional.empty();
        }
    }
}
