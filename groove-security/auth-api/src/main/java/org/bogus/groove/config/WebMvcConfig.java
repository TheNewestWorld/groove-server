package org.bogus.groove.config;


import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.Password;
import org.bogus.groove.config.interceptor.SecurityContextInjectionInterceptor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
class WebMvcConfig implements WebMvcConfigurer {
    private final SecurityContextInjectionInterceptor securityContextInjectionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
            .addInterceptor(securityContextInjectionInterceptor)
            .addPathPatterns("/api/**");
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapperConfig() {
        return builder -> builder.deserializerByType(Password.class, new PasswordDeserializer());
    }
}
