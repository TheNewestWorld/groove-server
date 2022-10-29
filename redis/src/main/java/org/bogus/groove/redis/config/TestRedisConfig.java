package org.bogus.groove.redis.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;

@Profile({"test", "local"})
@Configuration
@EnableAutoConfiguration
public class TestRedisConfig {
    private final RedisServer redisServer;

    public TestRedisConfig(RedisProperties redisProperties) {
        this.redisServer = RedisServer.builder()
            .port(redisProperties.getPort())
            .setting("maxmemory 128M")
            .build();
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}
