package org.bogus.groove.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.Password;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final List<HandlerInterceptor> interceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        interceptors.stream()
            .filter((interceptor) -> interceptor.getClass().getPackageName().startsWith("org.bogus.groove"))
            .forEach((interceptor) -> {
                registry
                    .addInterceptor(interceptor)
                    .addPathPatterns("/api/**");
            });
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapperConfig() {
        return builder -> builder.deserializerByType(Password.class, new PasswordDeserializer());
    }
}
