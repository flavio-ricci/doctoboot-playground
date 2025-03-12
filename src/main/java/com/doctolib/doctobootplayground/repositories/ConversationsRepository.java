package com.doctolib.doctobootplayground.repositories;

import com.doctolib.doctobootplayground.entities.ConversationEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConversationsRepository extends JpaRepository<ConversationEntity, UUID> {
    @NotNull List<ConversationEntity> findAll();

    List<ConversationEntity> findByMessagingProfileId(Long messagingProfileId, Sort sort);
}
