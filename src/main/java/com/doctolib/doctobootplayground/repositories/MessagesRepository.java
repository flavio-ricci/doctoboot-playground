package com.doctolib.doctobootplayground.repositories;

import com.doctolib.doctobootplayground.entities.MessageEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends JpaRepository<MessageEntity, UUID> {
    List<MessageEntity> findByConversationId(UUID conversationId, Sort sort);
}
