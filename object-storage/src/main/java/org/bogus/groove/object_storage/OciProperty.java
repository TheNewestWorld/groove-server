package org.bogus.groove.object_storage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "oci")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class OciProperty {
    private final String config;
    private final String bucketName;
}
