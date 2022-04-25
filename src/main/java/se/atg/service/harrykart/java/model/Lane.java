package se.atg.service.harrykart.java.model;

import java.util.Objects;

public class Lane {
    public final int number;

    public static Lane of(int number){
        return new Lane(number);
    }

    private Lane(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Lane{" +
                "number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lane lane = (Lane) o;
        return number == lane.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
