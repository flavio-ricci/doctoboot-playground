package com.doctolib.doctobootplayground.configuration;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@Configuration
public class SecurityFilterConfiguration {
    private static final String INTERNAL_PATH = "/__internal__/**";

    @Bean
    @ConditionalOnProperty(name = "doctoboot.authentication.enabled", havingValue = "false")
    public SecurityFilterChain openEndpointsSecurityFilterChain(HttpSecurity http) throws Exception {
        var openEndpointMatchers = new NegatedRequestMatcher(antMatcher(INTERNAL_PATH));
        return http.securityMatcher(openEndpointMatchers)
                .authorizeHttpRequests(request -> request.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    @ConditionalOnProperty(name = "doctoboot.authentication.enabled", havingValue = "true")
    public SecurityFilterChain openEndpointsSecurityFilterChainWithAnonymousAuth(
            HttpSecurity http,
            @Qualifier("anonymousAuthorizationAuthenticationManagerResolver")
                    AuthenticationManagerResolver<HttpServletRequest> anonymousAuthenticationManagerResolver,
            @Qualifier("httpAuthorizationBearerTokenResolver") BearerTokenResolver bearerTokenResolver)
            throws Exception {
        var openEndpointMatchers = new NegatedRequestMatcher(antMatcher(INTERNAL_PATH));
        return http.securityMatcher(openEndpointMatchers)
                .authorizeHttpRequests(request -> request.anyRequest().permitAll())
                .oauth2ResourceServer(oauth2 -> {
                    oauth2.authenticationManagerResolver(anonymousAuthenticationManagerResolver);
                    oauth2.bearerTokenResolver(bearerTokenResolver);
                })
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    @ConditionalOnProperty(name = "doctoboot.authentication.enabled", havingValue = "true")
    public SecurityFilterChain interserviceSecurityFilterJwtAuthorization(
            HttpSecurity http,
            @Qualifier("interserviceAuthorizationAuthenticationManagerResolver")
                    AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver,
            @Qualifier("interserviceAuthorizationBearerTokenResolver") BearerTokenResolver bearerTokenResolver)
            throws Exception {
        return http.securityMatcher(INTERNAL_PATH)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> {
                    oauth2.authenticationManagerResolver(authenticationManagerResolver);
                    oauth2.bearerTokenResolver(bearerTokenResolver);
                })
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
