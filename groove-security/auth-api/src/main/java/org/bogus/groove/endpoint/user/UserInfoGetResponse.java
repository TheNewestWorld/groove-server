package org.bogus.groove.endpoint.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.ProviderType;
import org.bogus.groove.common.enumeration.UserRole;

@Getter
@RequiredArgsConstructor
public class UserInfoGetResponse {
    private final Long id;
    private final String email;
    private final ProviderType providerType;
    private final String nickname;
    private final String profileUri;
    private final UserRole role;
}
