package se.atg.service.harrykart.java.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class RaceConfig {
    public final int noOfLoops;
    public final Map<Lane, Participant> participants;

    public RaceConfig(int noOfLoops, Map<Lane, Participant> participants) {
        this.noOfLoops = noOfLoops;
        this.participants = Collections.unmodifiableMap(participants);
    }

    public Collection<Participant> getParticipants() {
        return this.participants.values();
    }

    public Participant getParticipant(Lane lane) {
        return this.participants.get(lane);
    }

}
