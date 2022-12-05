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
    private final String user;
    private final String fingerprint;
    private final String tenancy;
    private final String region;
    private final String keyFile;
    private final String bucketName;
}
