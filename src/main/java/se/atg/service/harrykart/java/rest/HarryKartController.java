package se.atg.service.harrykart.java.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.atg.service.harrykart.java.Converter;
import se.atg.service.harrykart.java.model.HarryKartResult;
import se.atg.service.harrykart.java.service.HarryKartService;
import se.atg.xml.model.HarryKartType;

@RestController
@RequestMapping("/java/api")
public class HarryKartController {

    private static final int TOP_LIST_SIZE = 3;

    @Autowired
    HarryKartService service;

    @PostMapping(path = "/play", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HarryKartResult playHarryKart(@RequestBody HarryKartType harryKart) {
        // Convert HarryKartType XML into our own model, RaceConfig
        var raceConfig = Converter.convertToRaceConfig(harryKart);

        // Perform the race
        var raceResults = service.performRace(raceConfig);

        // Return sorted top list
        return Converter.convertToHarryKartResult(raceResults, TOP_LIST_SIZE);
    }

}
