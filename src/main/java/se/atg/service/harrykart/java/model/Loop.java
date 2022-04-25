package se.atg.service.harrykart.java.model;

import java.util.Objects;

public class Loop {

    public final int number;

    public static Loop of(int number) {
        return new Loop(number);
    }

    private Loop(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Loop{" +
                "number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loop loop = (Loop) o;
        return number == loop.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
