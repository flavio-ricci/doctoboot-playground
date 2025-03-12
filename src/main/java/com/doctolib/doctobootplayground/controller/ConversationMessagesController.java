package com.doctolib.doctobootplayground.controller;

import com.doctolib.doctobootplayground.dto.MessageDTO;
import com.doctolib.doctobootplayground.services.MessagesService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/conversations")
@RestController
public class ConversationMessagesController {
    private final MessagesService messagesService;

    public ConversationMessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @GetMapping("/{conversationId}/messages")
    public List<MessageDTO> getAllMessagesByConversationId(@PathVariable UUID conversationId) {
        return messagesService.getAllByConversationId(conversationId);
    }
}
