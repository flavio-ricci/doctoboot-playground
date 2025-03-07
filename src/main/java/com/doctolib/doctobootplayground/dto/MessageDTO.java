package com.doctolib.doctobootplayground.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MessageDTO {
    private UUID id;
    private UUID conversationId;
    private String status;
    private String source;
    private String tankerEncryptedBody;
    private Long creatorDoctorAccountId;
    private String documentIds;
    private Date viewedByPatientAt;
    private Date viewedByPractitionerAt;
    private Date deletedAt;
    private UUID externalId;
    private Date createdAt;
    private Date updatedAt;
    private Long creatorPatientAccountId;
    private Date sentAt;
    private String serverEncryptedBody;
}