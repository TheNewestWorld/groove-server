package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.BadRequestException;
import org.bogus.groove.common.exception.ErrorType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegister {
    private final UserCreator userCreator;
    private final UserReader userReader;

    public User register(UserRegisterParam param) {
        validateNotDuplicated(param);
        return userCreator.create(param);
    }

    private void validateNotDuplicated(UserRegisterParam param) {
        if (userReader.isExists(param.getEmail(), param.getProviderType())) {
            throw new BadRequestException(ErrorType.DUPLICATED_USER);
        }
        if (userReader.isExists(param.getNickname())) {
            throw new BadRequestException(ErrorType.DUPLICATED_NICKNAME);
        }
    }
}
