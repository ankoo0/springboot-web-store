package com.project.springbootwebstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AttributeResponse(@JsonProperty("name") String name,@JsonProperty("data") AttributeData data) {
}
