package com.project.springbootwebstore.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Query {
    @JsonProperty("query")
    private final String query;

    @JsonCreator
    public Query(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
