package org.bogus.groove.domain.user;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.BadRequestException;
import org.bogus.groove.common.exception.ErrorType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRegister userRegister;
    private final UserInfoFinder userInfoFinder;
    private final UserReader userReader;

    @Transactional
    public UserInfo register(UserRegisterParam param) {
        if (isDuplicated(param)) {
            throw new BadRequestException(ErrorType.DUPLICATED_USER);
        }

        return userRegister.register(param.getEmail(), param.getPassword(), param.getUserType());
    }

    public UserInfo getUserInfo(Long userId) {
        return userInfoFinder.find(userId);
    }

    private boolean isDuplicated(UserRegisterParam param) {
        return userReader.readOrNull(param.getEmail(), UserType.GROOVE).isPresent();
    }
}
