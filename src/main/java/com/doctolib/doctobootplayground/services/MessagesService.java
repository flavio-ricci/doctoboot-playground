package com.doctolib.doctobootplayground.services;

import com.doctolib.doctobootplayground.dto.MessageDTO;
import com.doctolib.doctobootplayground.entities.MessageEntity;
import com.doctolib.doctobootplayground.repositories.MessagesRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessagesService {
    private final MessagesRepository messagesRepository;

    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public List<MessageDTO> getAllByConversationId(UUID conversationId) {
        return messagesRepository.findByConversationId(conversationId, Sort.by(Sort.Direction.DESC, "createdAt")).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MessageDTO convertToDTO(MessageEntity messageEntity) {
        return MessageDTO.builder()
                .id(messageEntity.getId())
                .conversationId(messageEntity.getConversation().getId())
                .status(messageEntity.getStatus())
                .source(messageEntity.getSource())
                .tankerEncryptedBody(messageEntity.getTankerEncryptedBody())
                .creatorDoctorAccountId(messageEntity.getCreatorDoctorAccountId())
                .viewedByPatientAt(messageEntity.getViewedByPatientAt())
                .viewedByPractitionerAt(messageEntity.getViewedByPractitionerAt())
                .deletedAt(messageEntity.getDeletedAt())
                .externalId(messageEntity.getExternalId())
                .createdAt(messageEntity.getCreatedAt())
                .updatedAt(messageEntity.getUpdatedAt())
                .creatorPatientAccountId(messageEntity.getCreatorPatientAccountId())
                .sentAt(messageEntity.getSentAt())
                .serverEncryptedBody(messageEntity.getServerEncryptedBody())
                .build();
    }
}