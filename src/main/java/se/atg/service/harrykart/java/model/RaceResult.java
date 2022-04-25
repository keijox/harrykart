package se.atg.service.harrykart.java.model;

public class RaceResult {

    public final Participant participant;
    public final double totalRaceTime;

    public RaceResult(Participant participant, double totalRaceTime) {
        this.participant = participant;
        this.totalRaceTime = totalRaceTime;
    }

    public double getTotalRaceTime() {
        return totalRaceTime;
    }
}
