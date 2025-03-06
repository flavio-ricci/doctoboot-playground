package com.doctolib.doctobootplayground.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "patient_messaging_pro_conversations")
public class ConversationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column
    Long messagingProfileId;

    @Column
    Long patientId;

    @Column
    String source;

    @Column
    Long creatorDoctorAccountId;

    @Column
    String status;

    @Column
    UUID externalId;

    @Column
    String tankerEncryptedNote;

    @Column
    UUID motiveId;

    @Column
    Date lastActivityAt;

    @Column
    Date createdAt;

    @Column
    Date updatedAt;

    @Column
    Boolean canPatientReply;

    @Column
    Long creatorPatientAccountId;

    @OneToMany(mappedBy = "conversation")
    List<MessageEntity> messages;
}
