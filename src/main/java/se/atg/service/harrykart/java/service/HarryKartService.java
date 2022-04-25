package se.atg.service.harrykart.java.service;

import org.springframework.stereotype.Service;
import se.atg.service.harrykart.java.model.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HarryKartService {

    private static final double LOOP_LENGTH = 1000.0;
    private static final PowerUp NO_POWERUP = PowerUp.of(0);

    public List<RaceResult> performRace(RaceConfig raceConfig) {
        // Prepare storage of speeds
        Map<Participant, AtomicInteger> raceState = new HashMap<>();
        raceConfig.getParticipants().forEach(
            p -> raceState.put(p, new AtomicInteger(p.baseSpeed.value))
        );

        // Perform race loops, store speeds
        IntStream.range(0, raceConfig.noOfLoops)
            .boxed()
            .map(Loop::of)
            .forEach(finishedLoop ->
                raceConfig.getParticipants().forEach(
                    p -> {
                        //saveLoopSpeed(p, speeds.get(p), finishedLoop);
                        AtomicInteger participantSpeed = raceState.get(p);
                        participantSpeed.addAndGet(getSpeedChange(p, finishedLoop));
                        p.addLoopTime(calculateLoopTime(Speed.of(participantSpeed.get())));
                    }
                )
            );

        // Collect sorted results as RaceResult
        return raceConfig.getParticipants().stream()
            .map(p -> new RaceResult(p, p.getLoopTimes().stream().mapToDouble(d -> d).sum()))
            .sorted(Comparator.comparingDouble(RaceResult::getTotalRaceTime))
            .collect(Collectors.toList());
    }

    private static int getSpeedChange(Participant p, Loop loop) {
        return Optional.ofNullable(p.getPowerUp(loop)).orElse(NO_POWERUP).value;
    }

    private static double calculateLoopTime(Speed currentSpeed) {
        return LOOP_LENGTH / currentSpeed.value;
    }

}
