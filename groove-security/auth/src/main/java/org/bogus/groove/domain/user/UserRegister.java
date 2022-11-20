package org.bogus.groove.domain.user;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.BadRequestException;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.domain.user.authority.Authority;
import org.bogus.groove.domain.user.authority.UserAuthorityUpdater;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegister {
    private final UserCreator userCreator;
    private final UserAuthorityUpdater userAuthorityUpdater;
    private final UserReader userReader;

    @Transactional
    public User register(UserRegisterParam param) {
        validateNotDuplicated(param);

        var created = userCreator.create(param);
        userAuthorityUpdater.update(created.getId(), List.of(Authority.INACTIVE));
        return userReader.read(created.getId());
    }

    private void validateNotDuplicated(UserRegisterParam param) {
        if (userReader.isExists(param.getEmail(), param.getUserType())) {
            throw new BadRequestException(ErrorType.DUPLICATED_USER);
        }
        if (userReader.isExists(param.getNickname())) {
            throw new BadRequestException(ErrorType.DUPLICATED_NICKNAME);
        }
    }
}
