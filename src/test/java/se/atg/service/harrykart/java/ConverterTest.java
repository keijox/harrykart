package se.atg.service.harrykart.java;

import org.junit.jupiter.api.Test;
import se.atg.service.harrykart.java.model.*;
import se.atg.xml.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {


    @Test
    public void testConvertHarryKartType() {
        HarryKartType harry = new HarryKartType();
        StartListType startListType = new StartListType();
        ParticipantType participantType = new ParticipantType();
        participantType.setBaseSpeed(5);
        participantType.setLane(100);
        participantType.setName("Pålle");
        startListType.getParticipant().add(participantType);
        harry.setStartList(startListType);
        harry.setNumberOfLoops(3);

        PowerUpsType powerUpsType = new PowerUpsType();
        LoopType loop8 = new LoopType();
        loop8.setNumber(8);
        LaneType lane_a = new LaneType();
        lane_a.setNumber(100);
        lane_a.setValue(7);
        loop8.getLane().add(lane_a);
        powerUpsType.getLoop().add(loop8);

        LoopType loop9 = new LoopType();
        loop9.setNumber(9);
        LaneType lane_b = new LaneType();
        lane_b.setNumber(100);
        lane_b.setValue(9);
        loop9.getLane().add(lane_b);
        powerUpsType.getLoop().add(loop9);
        harry.setPowerUps(powerUpsType);

        RaceConfig raceConfig = Converter.convertToRaceConfig(harry);

        assertEquals(3, raceConfig.noOfLoops);
        assertEquals(1, raceConfig.getParticipants().size());
        Participant participant = raceConfig.getParticipant(Lane.of(100));
        assertEquals("Pålle", participant.name);
        assertEquals(Speed.of(5), participant.baseSpeed);
        assertEquals(7, participant.getPowerUp(Loop.of(8)).value);
        assertEquals(9, participant.getPowerUp(Loop.of(9)).value);
    }

    @Test
    public void testConvertRaceResult() {
        List<RaceResult> results = new ArrayList<>();
        results.add(new RaceResult(new Participant("A", Speed.of(10), Collections.emptyMap()), 100.0));
        results.add(new RaceResult(new Participant("B", Speed.of(11), Collections.emptyMap()), 110.0));
        results.add(new RaceResult(new Participant("C", Speed.of(12), Collections.emptyMap()), 120.0));

        HarryKartResult harryKartResult = Converter.convertToHarryKartResult(results, 2);

        assertEquals(2, harryKartResult.getRankings().size());
        assertEquals(1, harryKartResult.getRankings().get(0).position);
        assertEquals("A", harryKartResult.getRankings().get(0).horse);
        assertEquals(2, harryKartResult.getRankings().get(1).position);
        assertEquals("B", harryKartResult.getRankings().get(1).horse);
    }
}
