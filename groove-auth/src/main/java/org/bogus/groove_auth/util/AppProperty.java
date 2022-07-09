package org.bogus.groove_auth.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "spring.application")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
@ToString
public class AppProperty {
    private final String name;
}