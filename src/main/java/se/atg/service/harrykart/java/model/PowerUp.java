package se.atg.service.harrykart.java.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PowerUp(int value) {

    public static PowerUp of(int value) {
        return new PowerUp(value);
    }

    public PowerUp(@JsonProperty("value") int value) {
        this.value = value;
    }
}
