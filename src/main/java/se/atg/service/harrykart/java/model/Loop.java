package se.atg.service.harrykart.java.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Loop(int number) {
    public static Loop of(int number) {
        return new Loop(number);
    }

    public Loop(@JsonProperty("number") int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Loop{" +
                "number=" + number +
                '}';
    }
}
