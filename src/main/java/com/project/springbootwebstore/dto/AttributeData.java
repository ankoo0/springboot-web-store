package com.project.springbootwebstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AttributeData(
        @JsonProperty("description") String description,
        @JsonProperty("values") List<String> values
) {
}
