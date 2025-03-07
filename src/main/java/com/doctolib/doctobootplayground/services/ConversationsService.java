package com.doctolib.doctobootplayground.services;

import com.doctolib.doctobootplayground.dto.ConversationDTO;
import com.doctolib.doctobootplayground.entities.ConversationEntity;
import com.doctolib.doctobootplayground.repositories.ConversationsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversationsService {
    private final ConversationsRepository conversationsRepository;

    public ConversationsService(ConversationsRepository conversationsRepository) {
        this.conversationsRepository = conversationsRepository;
    }

    public List<ConversationDTO> getAll() {
        return conversationsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ConversationDTO> getAllByMessagingProfileId(Long messagingProfileId) {
        return conversationsRepository.findByMessagingProfileId(messagingProfileId, Sort.by(Sort.Direction.DESC, "createdAt")).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ConversationDTO convertToDTO(ConversationEntity conversationEntity) {
        return ConversationDTO.builder()
                .id(conversationEntity.getId())
                .messagingProfileId(conversationEntity.getMessagingProfileId())
                .patientId(conversationEntity.getPatientId())
                .source(conversationEntity.getSource())
                .creatorDoctorAccountId(conversationEntity.getCreatorDoctorAccountId())
                .status(conversationEntity.getStatus())
                .externalId(conversationEntity.getExternalId())
                .tankerEncryptedNote(conversationEntity.getTankerEncryptedNote())
                .motiveId(conversationEntity.getMotiveId())
                .lastActivityAt(conversationEntity.getLastActivityAt())
                .createdAt(conversationEntity.getCreatedAt())
                .updatedAt(conversationEntity.getUpdatedAt())
                .canPatientReply(conversationEntity.getCanPatientReply())
                .creatorPatientAccountId(conversationEntity.getCreatorPatientAccountId())
                .build();
    }
}