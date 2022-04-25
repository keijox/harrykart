package se.atg.service.harrykart.java.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public record Lane(int number) {

    public static Lane of(int number) {
        return new Lane(number);
    }

    public Lane(@JsonProperty("number") int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Lane{" +
                "number=" + number +
                '}';
    }

}
