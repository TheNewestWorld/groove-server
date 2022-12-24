package org.bogus.groove.client.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.ProviderType;
import org.bogus.groove.common.enumeration.UserRole;

@Getter
@RequiredArgsConstructor
public class UserInfo {
    private final Long id;
    private final String email;
    private final ProviderType type;
    private final String nickname;
    private final String profileUri;
    private final UserRole role;
}
