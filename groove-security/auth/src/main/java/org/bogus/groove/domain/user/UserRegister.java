package org.bogus.groove.domain.user;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.exception.BadRequestException;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.object_storage.AttachmentCreateParam;
import org.bogus.groove.object_storage.AttachmentCreator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegister {
    private final UserCreator userCreator;
    private final UserReader userReader;
    private final AttachmentCreator attachmentCreator;

    @Transactional
    public User register(UserRegisterParam param) {
        validateNotDuplicated(param);

        var user = userCreator.create(param);
        createDefaultProfile(user.getId());
        return user;
    }

    private void createDefaultProfile(Long userId) {
        attachmentCreator.create(
            new AttachmentCreateParam(
                "default",
                null,
                "default",
                0,
                userId,
                AttachmentType.PROFILE
            )
        );
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
