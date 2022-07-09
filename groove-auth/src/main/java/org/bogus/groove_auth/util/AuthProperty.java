package org.bogus.groove_auth.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "authentication")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
@ToString
public class AuthProperty {
    private final String authKey;
    private final Long accessExpiration;
    private final Long refreshExpiration;
}
