package com.doctolib.doctobootplayground.services.authorization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessagingProfileAuthorizationService {

    private static final List<Long> ALLOWED_MESSAGING_PROFILE_IDS = List.of(400256745L);

    public boolean hasAccessToMessagingProfile(Long accountId, Long messagingProfileId) {
        boolean isAllowed = 12345L == accountId && ALLOWED_MESSAGING_PROFILE_IDS.contains(messagingProfileId);
        if (!isAllowed) {
            log.warn("Account {} tried to access messaging profile {} but is not allowed", accountId, messagingProfileId);
        }
        return isAllowed;
    }

}