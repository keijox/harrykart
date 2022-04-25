package se.atg.service.harrykart.java.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import se.atg.service.harrykart.java.Converter;
import se.atg.service.harrykart.java.model.HarryKartResult;
import se.atg.service.harrykart.java.model.RaceConfig;
import se.atg.service.harrykart.java.service.HarryKartService;
import se.atg.xml.model.HarryKartType;

import static se.atg.service.harrykart.java.constants.ApplicationSpecificConstants.API_DOCUMENTATION_TAG;
import static se.atg.service.harrykart.java.constants.ApplicationSpecificConstants.TOP_LIST_SIZE;


@RestController
@RequestMapping("/java/api")
@Tag(name = API_DOCUMENTATION_TAG, description = "HarryKart API Documentation")
@OpenAPIDefinition(info = @Info(title = API_DOCUMENTATION_TAG, version = "1.0", description = "HarryKart API Documentation"))
public class HarryKartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HarryKartController.class);

    @Autowired
    HarryKartService service;

    @Operation(summary = "Creates a Harry Kart Horse Race",
               tags = {API_DOCUMENTATION_TAG},
               description = "Creates a Harry Kart Horse Rac for given public request",
               responses = {
                       @ApiResponse(description = "Public Response with race results", content = @Content(
                               schema = @Schema(implementation = HarryKartController.class)
                       )),
                       @ApiResponse(responseCode = "400", description = "General exception on bad request"),
                       @ApiResponse(responseCode = "500", description = "Server Internal Error")
               })
    @PostMapping(path = "/play", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HarryKartResult playHarryKart(@RequestBody HarryKartType harryKart) {
        RaceConfig raceConfig;

        LOGGER.info("Request with body {}", harryKart);
        try {
            // Convert HarryKartType XML into our own model, RaceConfig
            raceConfig = Converter.convertToRaceConfig(harryKart);
        } catch (Exception exc) {
            LOGGER.error("Request with body {} failing wih exception {}", harryKart, exc);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request", exc);
        }

        try {
            // Perform the race
            var raceResults = service.performRace(raceConfig);

            // Return sorted top list
            var harryKartResult = Converter.convertToHarryKartResult(raceResults, TOP_LIST_SIZE);
            LOGGER.info("Response with body {}", harryKartResult);

            return harryKartResult;
        } catch (Exception exc) {
            LOGGER.error("Request with body {} failing wih exception {}", harryKart, exc);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", exc);
        }
    }

}
