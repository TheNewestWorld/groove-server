package org.bogus.groove.domain.user;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.BadRequestException;
import org.bogus.groove.common.exception.ErrorType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegister {
    private final UserCreator userCreator;
    private final UserReader userReader;

    @Transactional
    public User register(UserRegisterParam param) {
        validateNotDuplicated(param);

        var created = userCreator.create(param);
        return userReader.read(created.getId());
    }

    private void validateNotDuplicated(UserRegisterParam param) {
        if (userReader.readOrNull(param.getEmail(), param.getProviderType()).isPresent()) {
            throw new BadRequestException(ErrorType.DUPLICATED_USER);
        }
        if (userReader.readOrNull(param.getNickname()).isPresent()) {
            throw new BadRequestException(ErrorType.DUPLICATED_NICKNAME);
        }
    }
}
