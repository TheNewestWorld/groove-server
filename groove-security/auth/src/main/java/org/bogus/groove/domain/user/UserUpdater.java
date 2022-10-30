package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.UserEntity;
import org.bogus.groove.storage.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserUpdater {
    private final UserRepository userRepository;

    @Transactional
    public void update(long userId, String nickname) {
        var entity = getEntity(userId);
        entity.setNickname(nickname);
    }

    @Transactional
    public void update(long userId, long profileId) {
        var entity = getEntity(userId);
        entity.setProfileId(profileId);
    }

    private UserEntity getEntity(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_USER));
    }
}
