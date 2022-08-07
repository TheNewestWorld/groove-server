package org.bogus.groove_auth.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bogus.groove_auth.domain.user.token.TokenGenerator;
import org.bogus.groove_auth.storage.UserAuthorityRepository;
import org.bogus.groove_auth.storage.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final TokenGenerator tokenGenerator;
    private final ObjectMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        var securityFailureHandler = new SecurityFilterExceptionHandler(mapper);

        http
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .headers().frameOptions().sameOrigin()
        ;

        http
            .authorizeHttpRequests()
            .anyRequest()
            .permitAll()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(securityFailureHandler)
        ;

        http
            .addFilterAt(getRestfulAuthenticationFilter(securityFailureHandler), UsernamePasswordAuthenticationFilter.class)
        ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(getCustomDaoAuthenticationProvider());
    }

    private CustomDaoAuthenticationProvider getCustomDaoAuthenticationProvider() {
        var authenticationProvider = new CustomDaoAuthenticationProvider(passwordEncoder);
        authenticationProvider.setUserDetailsService(new CustomUserDetailsService(userRepository, userAuthorityRepository));
        return authenticationProvider;
    }

    private RestfulAuthenticationFilter getRestfulAuthenticationFilter(SecurityFilterExceptionHandler failureHandler) throws Exception {
        var authFilter = new RestfulAuthenticationFilter("/api/auth/login", mapper);
        authFilter.setAuthenticationManager(authenticationManagerBean());
        authFilter.setAuthenticationSuccessHandler(new RestfulAuthenticationSuccessHandler(tokenGenerator, mapper));
        authFilter.setAuthenticationFailureHandler(failureHandler);
        return authFilter;
    }
}
