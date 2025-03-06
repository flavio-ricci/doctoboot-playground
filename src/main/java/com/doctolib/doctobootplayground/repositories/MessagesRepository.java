package com.doctolib.doctobootplayground.repositories;

import com.doctolib.doctobootplayground.entities.MessageEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessagesRepository extends JpaRepository<MessageEntity, UUID> {
    List<MessageEntity> findByConversationId(UUID conversationId, Sort sort);
}