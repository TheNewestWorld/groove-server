package org.bogus.groove.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.config.authentication.GrooveAuthenticationProvider;
import org.bogus.groove.config.authentication.RestfulAuthenticationFailureHandler;
import org.bogus.groove.config.authentication.RestfulAuthenticationFilter;
import org.bogus.groove.config.authentication.RestfulAuthenticationSuccessHandler;
import org.bogus.groove.config.error.ExceptionTranslator;
import org.bogus.groove.config.error.FilterChainExceptionHandlingFilter;
import org.bogus.groove.domain.user.token.TokenGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenGenerator tokenGenerator;
    private final ObjectMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final ExceptionTranslator exceptionTranslator;
    private final GrooveUserDetailsService userDetailsService;

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
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .headers().frameOptions().sameOrigin()
        ;

        http
            .authorizeHttpRequests()
            .anyRequest()
            .permitAll()
        ;

        http
            .addFilterBefore(getRestfulAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//            .addFilterBefore(getJwtAuthorizationFilter(), AuthorizationFilter.class)
            .addFilterBefore(securityFilterExceptionHandler(), LogoutFilter.class)
        ;

//        http.oauth2Login()
//            .successHandler(new OAuth2AuthenticationSuccessHandler(userRegister, userInfoFinder, tokenGenerator))
//        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(getCustomDaoAuthenticationProvider());
    }

    private GrooveAuthenticationProvider getCustomDaoAuthenticationProvider() {
        var authenticationProvider = new GrooveAuthenticationProvider(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    private RestfulAuthenticationFilter getRestfulAuthenticationFilter() throws Exception {
        var authFilter = new RestfulAuthenticationFilter(new AntPathRequestMatcher("/api/auth/login", HttpMethod.POST.name()), mapper);
        authFilter.setAuthenticationManager(authenticationManagerBean());
        authFilter.setAuthenticationSuccessHandler(new RestfulAuthenticationSuccessHandler(tokenGenerator, mapper));
        authFilter.setAuthenticationFailureHandler(new RestfulAuthenticationFailureHandler());
        return authFilter;
    }

    private FilterChainExceptionHandlingFilter securityFilterExceptionHandler() {
        return new FilterChainExceptionHandlingFilter(mapper, exceptionTranslator);
    }
}
