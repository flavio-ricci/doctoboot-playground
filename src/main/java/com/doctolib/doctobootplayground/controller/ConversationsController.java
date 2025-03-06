package com.doctolib.doctobootplayground.controller;

import com.doctolib.doctobootplayground.dto.ConversationDTO;
import com.doctolib.doctobootplayground.services.ConversationsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/conversations")
@RestController
public class ConversationsController {
    private final ConversationsService conversationsService;

    public ConversationsController(ConversationsService conversationsService) {
        this.conversationsService = conversationsService;
    }

    @GetMapping
    public List<ConversationDTO> getAll() {
        return conversationsService.getAll();
    }

    @GetMapping("/{messagingProfileId}")
    public List<ConversationDTO> getAllByMessagingProfileId(@PathVariable Long messagingProfileId) {
        return conversationsService.getAllByMessagingProfileId(messagingProfileId);
    }
}
