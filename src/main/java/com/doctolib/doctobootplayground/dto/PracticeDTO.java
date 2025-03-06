package com.doctolib.doctobootplayground.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PracticeDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("organization_id")
    private Long organizationId;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("zipcode")
    private String zipcode;

    @JsonProperty("longitude")
    private Long longitude;

    @JsonProperty("latitude")
    private Long latitude;
}
