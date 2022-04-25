package se.atg.service.harrykart.java.service;

import org.junit.jupiter.api.Test;
import se.atg.service.harrykart.java.Converter;
import se.atg.service.harrykart.java.model.RaceConfig;
import se.atg.service.harrykart.java.model.RaceResult;
import se.atg.xml.model.HarryKartType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HarryKartServiceTest {

    HarryKartService serviceToTest = new HarryKartService();

    @Test
    public void test_performRace_1() throws Exception {
        // Given
        RaceConfig raceConfig = Converter.convertToRaceConfig(loadXmlFile("src/test/resources/input_0.xml"));

        // When
        List<RaceResult> raceResults = serviceToTest.performRace(raceConfig);

        // Then
        assertEquals(4, raceResults.size());
        assertEquals("TIMETOBELUCKY", raceResults.get(0).participant().name);
        assertEquals("HERCULES BOKO", raceResults.get(1).participant().name);
        assertEquals("CARGO DOOR", raceResults.get(2).participant().name);
        assertEquals("WAIKIKI SILVIO", raceResults.get(3).participant().name);
    }

    @Test
    public void test_performRace_2() throws Exception {
        // Given
        RaceConfig raceConfig = Converter.convertToRaceConfig(loadXmlFile("src/test/resources/input_1.xml"));

        // When
        List<RaceResult> raceResults = serviceToTest.performRace(raceConfig);

        // Then
        assertEquals(4, raceResults.size());
        assertEquals("WAIKIKI SILVIO", raceResults.get(0).participant().name);
        assertEquals("TIMETOBELUCKY", raceResults.get(1).participant().name);
        assertEquals("HERCULES BOKO", raceResults.get(2).participant().name);
        assertEquals("CARGO DOOR", raceResults.get(3).participant().name);
    }

    @Test
    public void test_performRace_3() throws Exception {
        // Given
        RaceConfig raceConfig = Converter.convertToRaceConfig(loadXmlFile("src/test/resources/input_2.xml"));

        // When
        List<RaceResult> raceResults = serviceToTest.performRace(raceConfig);

        // Then
        assertEquals(4, raceResults.size());
        assertEquals("HERCULES BOKO", raceResults.get(0).participant().name);
        assertEquals("TIMETOBELUCKY", raceResults.get(1).participant().name);
        assertEquals("WAIKIKI SILVIO", raceResults.get(2).participant().name);
        assertEquals("CARGO DOOR", raceResults.get(3).participant().name);
    }

    private static HarryKartType loadXmlFile(String xmlPath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(HarryKartType.class);
        return jaxbContext.createUnmarshaller()
                          .unmarshal(new StreamSource(new File(xmlPath)), HarryKartType.class).getValue();
    }
}
