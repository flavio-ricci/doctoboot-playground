package com.doctolib.doctobootplayground.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "patient_messaging_pro_messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    ConversationEntity conversation;

//    @Column
//    UUID conversationId;

    @Column
    String status;

    @Column
    String source;

    @Column
    String tankerEncryptedBody;

    @Column
    Long creatorDoctorAccountId;

    @Column
    Date viewedByPatientAt;

    @Column
    Date viewedByPractitionerAt;

    @Column
    Date deletedAt;

    @Column
    UUID externalId;

    @Column
    Date createdAt;

    @Column
    Date updatedAt;

    @Column
    Long creatorPatientAccountId;

    @Column
    Date sentAt;

    @Column
    String serverEncryptedBody;
}