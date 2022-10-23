package org.bogus.groove.domain.user;

import java.util.List;
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

    public UserInfo register(UserRegisterParam param) {
        validateNotDuplicated(param);

        var created = userCreator.create(param);
        var authorities = userAuthorityUpdater.update(created.getId(), List.of(Authority.USER));
        return new UserInfo(
            created.getId(),
            created.getEmail(),
            created.getType(),
            authorities
        );
    }

    private void validateNotDuplicated(UserRegisterParam param) {
        if (userReader.readOrNull(param.getEmail(), param.getUserType()).isPresent()) {
            throw new BadRequestException(ErrorType.DUPLICATED_USER);
        }
        if (userReader.readOrNull(param.getNickname()).isPresent()) {
            throw new BadRequestException(ErrorType.DUPLICATED_NICKNAME);
        }
    }
}
