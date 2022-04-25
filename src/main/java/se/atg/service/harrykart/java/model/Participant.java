package se.atg.service.harrykart.java.model;

import java.util.*;

public class Participant {
    public final String name;
    public final Speed baseSpeed;
    public final Map<Loop, PowerUp> powerups;
    // dynamic properties
    private final List<Double> loopTimes;

    public Participant(String name, Speed baseSpeed, Map<Loop, PowerUp> powerups) {
        this.name = name;
        this.baseSpeed = baseSpeed;
        this.powerups = Collections.unmodifiableMap(powerups);
        this.loopTimes = new ArrayList<>();
    }

    public PowerUp getPowerUp(Loop loop) {
        return this.powerups.get(loop);
    }

    public void addLoopTime(double time) {
        this.loopTimes.add(time);
    }

    public List<Double> getLoopTimes(){
        return Collections.unmodifiableList(this.loopTimes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
