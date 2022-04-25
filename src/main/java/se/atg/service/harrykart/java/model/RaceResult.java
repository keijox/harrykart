package se.atg.service.harrykart.java.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RaceResult(Participant participant, double totalRaceTime) {

    public RaceResult(@JsonProperty("participant") Participant participant, @JsonProperty("totalRaceTime") double totalRaceTime) {
        this.participant = participant;
        this.totalRaceTime = totalRaceTime;
    }

}
