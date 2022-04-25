package se.atg.service.harrykart.java.model;

public class PowerUp {

    public final int value;

    public static PowerUp of(int value) {
        return new PowerUp(value);
    }

    private PowerUp(int value) {
        this.value = value;
    }

}
