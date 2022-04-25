package se.atg.service.harrykart.java.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public record Speed(int value) {

    public static Speed of(int value) {
        return new Speed(value);
    }

    public Speed(@JsonProperty("value") int value) {
        this.value = value;
    }

}
