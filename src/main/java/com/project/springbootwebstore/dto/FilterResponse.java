package com.project.springbootwebstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FilterResponse(@JsonProperty("attribute-data") List<AttributeResponse> attributeData) {
}
