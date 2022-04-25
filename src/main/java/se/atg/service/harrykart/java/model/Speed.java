package se.atg.service.harrykart.java.model;

import java.util.Objects;

public class Speed {

    public final int value;

    public static Speed of(int value) {
        return new Speed(value);
    }

    public Speed add(int increment) {
        return new Speed(this.value + increment);
    }

    private Speed(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Speed speed = (Speed) o;
        return value == speed.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
