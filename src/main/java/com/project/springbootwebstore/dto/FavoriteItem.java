package com.project.springbootwebstore.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class FavoriteItem {

    private final int id;
    @JsonCreator
    public FavoriteItem( @JsonProperty(value = "id") int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
