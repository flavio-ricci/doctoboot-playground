package com.doctolib.doctobootplayground.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ConversationDTO {
    private UUID id;
    private Long messagingProfileId;
    private Long patientId;
    private String source;
    private Long creatorDoctorAccountId;
    private String status;
    private UUID externalId;
    private String tankerEncryptedNote;
    private UUID motiveId;
    private Date lastActivityAt;
    private Date createdAt;
    private Date updatedAt;
    private Boolean canPatientReply;
    private Long creatorPatientAccountId;
}
