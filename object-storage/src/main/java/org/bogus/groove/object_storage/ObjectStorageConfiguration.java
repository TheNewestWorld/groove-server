package org.bogus.groove.object_storage;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.http.client.Options;
import com.oracle.bmc.http.client.StandardClientProperties;
import com.oracle.bmc.http.client.jersey.ApacheClientProperties;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import java.io.IOException;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class ObjectStorageConfiguration {
    @Profile({"local", "test"})
    @Primary
    @Bean
    public ObjectStorage fileSystemBasedObjectStorage() {
        return new FileSystemBasedObjectStorage();
    }

    @Profile("dev")
    @Primary
    @Bean
    public ObjectStorage ociBucketBasedObjectStorage(ObjectStorageClient objectStorageClient, OciProperty ociProperty) {
        return new OciBucketBasedObjectStorage(objectStorageClient, ociProperty);
    }

    @Profile("dev")
    @Primary
    @Bean
    public ObjectStorageClient objectStorageClient(OciProperty ociProperty) throws IOException {
        Options.shouldAutoCloseResponseInputStream(false);

        return ObjectStorageClient.builder()
            .clientConfigurator(builder ->
                builder
                    .property(StandardClientProperties.BUFFER_REQUEST, false)
                    .property(ApacheClientProperties.CONNECTION_MANAGER, getPoolConnectionManager())
            )
            .build(getConfigFileAuthenticationDetailsProvider(ociProperty));
    }

    private ConfigFileAuthenticationDetailsProvider getConfigFileAuthenticationDetailsProvider(OciProperty ociProperty) throws IOException {
        var config = ConfigFileReader.parse(ociProperty.getConfig());
        return new ConfigFileAuthenticationDetailsProvider(config);
    }

    private static PoolingHttpClientConnectionManager getPoolConnectionManager() {
        var poolConnectionManager = new PoolingHttpClientConnectionManager();
        poolConnectionManager.setMaxTotal(100);
        poolConnectionManager.setDefaultMaxPerRoute(50);
        return poolConnectionManager;
    }
}
