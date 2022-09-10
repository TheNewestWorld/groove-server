package org.bogus.groove.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.UserInfoFinder;
import org.bogus.groove.domain.user.token.TokenGenerator;
import org.bogus.groove.domain.user.token.TokenValidator;
import org.bogus.groove.storage.UserAuthorityRepository;
import org.bogus.groove.storage.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final TokenGenerator tokenGenerator;
    private final TokenValidator tokenValidator;
    private final UserInfoFinder userInfoFinder;
    private final ObjectMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final ExceptionTranslator exceptionTranslator;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
        ;

        http
            .addFilterAt(getRestfulAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(getJwtAuthorizationFilter(), AuthorizationFilter.class)
            .addFilterBefore(securityFilterExceptionHandler(), CorsFilter.class)
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

    private RestfulAuthenticationFilter getRestfulAuthenticationFilter() throws Exception {
        var authFilter = new RestfulAuthenticationFilter(new AntPathRequestMatcher("/api/auth/login", HttpMethod.POST.name()), mapper);
        authFilter.setAuthenticationManager(authenticationManagerBean());
        authFilter.setAuthenticationSuccessHandler(new RestfulAuthenticationSuccessHandler(tokenGenerator, mapper));
        return authFilter;
    }

    private JwtAuthorizationFilter getJwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(tokenValidator, userInfoFinder);
    }

    private FilterChainExceptionHandlingFilter securityFilterExceptionHandler() {
        return new FilterChainExceptionHandlingFilter(mapper, exceptionTranslator);
    }
}