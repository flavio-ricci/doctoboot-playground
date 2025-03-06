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
        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.setId(conversationEntity.getId());
        conversationDTO.setMessagingProfileId(conversationEntity.getMessagingProfileId());
        conversationDTO.setPatientId(conversationEntity.getPatientId());
        conversationDTO.setSource(conversationEntity.getSource());
        conversationDTO.setCreatorDoctorAccountId(conversationEntity.getCreatorDoctorAccountId());
        conversationDTO.setStatus(conversationEntity.getStatus());
        conversationDTO.setExternalId(conversationEntity.getExternalId());
        conversationDTO.setTankerEncryptedNote(conversationEntity.getTankerEncryptedNote());
        conversationDTO.setMotiveId(conversationEntity.getMotiveId());
        conversationDTO.setLastActivityAt(conversationEntity.getLastActivityAt());
        conversationDTO.setCreatedAt(conversationEntity.getCreatedAt());
        conversationDTO.setUpdatedAt(conversationEntity.getUpdatedAt());
        conversationDTO.setCanPatientReply(conversationEntity.getCanPatientReply());
        conversationDTO.setCreatorPatientAccountId(conversationEntity.getCreatorPatientAccountId());
        return conversationDTO;
    }
}