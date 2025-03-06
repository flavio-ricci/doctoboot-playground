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
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(messageEntity.getId());
        messageDTO.setConversationId(messageEntity.getConversation().getId());
        messageDTO.setStatus(messageEntity.getStatus());
        messageDTO.setSource(messageEntity.getSource());
        messageDTO.setTankerEncryptedBody(messageEntity.getTankerEncryptedBody());
        messageDTO.setCreatorDoctorAccountId(messageEntity.getCreatorDoctorAccountId());
        messageDTO.setViewedByPatientAt(messageEntity.getViewedByPatientAt());
        messageDTO.setViewedByPractitionerAt(messageEntity.getViewedByPractitionerAt());
        messageDTO.setDeletedAt(messageEntity.getDeletedAt());
        messageDTO.setExternalId(messageEntity.getExternalId());
        messageDTO.setCreatedAt(messageEntity.getCreatedAt());
        messageDTO.setUpdatedAt(messageEntity.getUpdatedAt());
        messageDTO.setCreatorPatientAccountId(messageEntity.getCreatorPatientAccountId());
        messageDTO.setSentAt(messageEntity.getSentAt());
        messageDTO.setServerEncryptedBody(messageEntity.getServerEncryptedBody());
        return messageDTO;
    }
}