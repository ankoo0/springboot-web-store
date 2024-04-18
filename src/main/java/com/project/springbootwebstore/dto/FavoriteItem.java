package com.project.springbootwebstore.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class FavoriteItem {

    private final long id;
    @JsonCreator
    public FavoriteItem( @JsonProperty(value = "id") int id) {
        this.id = id;
    }

}
