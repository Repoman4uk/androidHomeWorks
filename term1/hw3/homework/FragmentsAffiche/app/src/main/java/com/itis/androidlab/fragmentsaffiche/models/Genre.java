package com.itis.androidlab.fragmentsaffiche.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
@JsonIgnoreProperties(ignoreUnknown = true)
public enum Genre {
    ACTION(1, "action"), CARTOON(2, "cartoon"), FANTASTIC(3, "fantastic"), UNKNOWN(0, "unknown");
    private Integer id;
    private String name;

    private Genre(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public static Genre getDefault() {
        return UNKNOWN;
    }
}
