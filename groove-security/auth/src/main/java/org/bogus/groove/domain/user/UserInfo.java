package org.bogus.groove.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.ProviderType;
import org.bogus.groove.common.enumeration.UserRole;

@Getter
@RequiredArgsConstructor
public class UserInfo {
    private final Long id;
    private final String email;
    private final ProviderType providerType;
    private final String nickName;
    private final String profileUri;
    private final UserRole role;
}
