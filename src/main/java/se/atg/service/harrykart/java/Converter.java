package se.atg.service.harrykart.java;

import se.atg.service.harrykart.java.model.*;
import se.atg.xml.model.HarryKartType;
import se.atg.xml.model.ParticipantType;
import se.atg.xml.model.StartListType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Converter {

    public static RaceConfig convertToRaceConfig(HarryKartType harryKart) {
        Map<Lane, Map<Loop, PowerUp>> powerups = convertPowerUps(harryKart);
        Map<Lane, Participant> participants = convertParticipants(harryKart, powerups);
        return new RaceConfig(harryKart.getNumberOfLoops(), participants);
    }

    public static HarryKartResult convertToHarryKartResult(List<RaceResult> raceResults, int maxRows) {
        HarryKartResult harryKartResult = new HarryKartResult();
        AtomicInteger racePosition = new AtomicInteger(1);
        raceResults.stream()
            .limit(maxRows)
            .forEach(r -> harryKartResult.addRanking(racePosition.getAndIncrement(), r.participant.name));
        return harryKartResult;
    }


    private static Map<Lane, Participant> convertParticipants(HarryKartType harryKart, Map<Lane, Map<Loop, PowerUp>> powerups) {
        Map<Lane, Participant> participants = new HashMap<>();
        harryKart.getStartList().getParticipant().forEach(
            p -> {
                Lane lane = Lane.of(p.getLane());
                participants.put(lane, mapToParticipant(p, powerups.get(lane)));
            }
        );
        return participants;
    }

    private static Map<Lane, Map<Loop, PowerUp>> convertPowerUps(HarryKartType harryKart) {
        Map<Lane, Map<Loop, PowerUp>> powerups = new HashMap<>();
        harryKart.getPowerUps().getLoop().forEach(
            loopType ->
                loopType.getLane().forEach(
                    laneType -> {
                        Lane lane = Lane.of(laneType.getNumber());
                        powerups.putIfAbsent(lane, new HashMap<>());
                        powerups.get(lane).put(Loop.of(loopType.getNumber()), PowerUp.of(laneType.getValue()));
                    }
                )
        );
        return powerups;
    }

    private static Participant mapToParticipant(ParticipantType p, Map<Loop, PowerUp> participantPowerups) {
        return new Participant(p.getName(), Speed.of(p.getBaseSpeed()), participantPowerups);
    }

}
