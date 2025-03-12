package com.doctolib.doctobootplayground.configuration;

import com.doctolib.doctobootplayground.services.authorization.MessagingProfileAuthorizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MessagingSecurityConfiguration {

    public MessagingSecurityConfiguration(MessagingProfileAuthorizationService authService) {
        this.messagingProfileAuthorizationService = authService;
    }

    private final MessagingProfileAuthorizationService messagingProfileAuthorizationService;

    @Bean
    public SecurityFilterChain messagingFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/conversations/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/conversations/{messagingProfileId}/**")
                        .access((authentication, context) -> {
                            Long profileId = Long.parseLong(context.getVariables().get("messagingProfileId"));
                            Long accountId = 12345L;// ottieni l'account ID dal token JWT
                            return new AuthorizationDecision(
                                    messagingProfileAuthorizationService.hasAccessToMessagingProfile(accountId, profileId)
                            );
                        })
                )
                .build();
    }
}