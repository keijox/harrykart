package se.atg.service.harrykart.java;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.net.URI;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("java-test")
public class HarryKartAppTest {

    private final static URI harryKartApp = URI.create("http://localhost:8181/java/api/play");

    @BeforeAll
    void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("Trying to GET instead of POST should return 405 Method not allowed")
    void useGetOnPostEndpointShouldNotBePossible() {
        when()
                .get(harryKartApp)
                .then()
                .assertThat()
                .statusCode(405);
    }

    @Test
    @DisplayName("Test race #1")
    void testRace1() {
        given()
                .header("Content-Type", ContentType.XML)
                .body(new File("src/main/resources/input_0.xml"))
                .when()
                .post(harryKartApp)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .header("Content-Type", ContentType.JSON.toString())
                .and()
                .body("ranking.size()", equalTo(3))
                .body("ranking[0].position", equalTo(1))
                .body("ranking[0].horse", equalTo("TIMETOBELUCKY"))
                .body("ranking[1].position", equalTo(2))
                .body("ranking[1].horse", equalTo("HERCULES BOKO"))
                .body("ranking[2].position", equalTo(3))
                .body("ranking[2].horse", equalTo("CARGO DOOR"));
    }

    @Test
    @DisplayName("Test race #2")
    void testRace2() {
        given()
                .header("Content-Type", ContentType.XML)
                .body(new File("src/main/resources/input_1.xml"))
                .when()
                .post(harryKartApp)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .header("Content-Type", ContentType.JSON.toString())
                .and()
                .body("ranking.size()", equalTo(3))
                .body("ranking[0].position", equalTo(1))
                .body("ranking[0].horse", equalTo("WAIKIKI SILVIO"))
                .body("ranking[1].position", equalTo(2))
                .body("ranking[1].horse", equalTo("TIMETOBELUCKY"))
                .body("ranking[2].position", equalTo(3))
                .body("ranking[2].horse", equalTo("HERCULES BOKO"));
    }

    @Test
    @DisplayName("Test race #3")
    void testRace3() {
        given()
                .header("Content-Type", ContentType.XML)
                .body(new File("src/main/resources/input_2.xml"))
                .when()
                .post(harryKartApp)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .header("Content-Type", ContentType.JSON.toString())
                .and()
                .body("ranking.size()", equalTo(3))
                .body("ranking[0].position", equalTo(1))
                .body("ranking[0].horse", equalTo("HERCULES BOKO"))
                .body("ranking[1].position", equalTo(2))
                .body("ranking[1].horse", equalTo("TIMETOBELUCKY"))
                .body("ranking[2].position", equalTo(3))
                .body("ranking[2].horse", equalTo("WAIKIKI SILVIO"));
    }

    @Test
    @DisplayName("Test bad input request parsing")
    void testBadInputRequestParsing() {
        given()
                .header("Content-Type", ContentType.XML)
                .body(new File("src/main/resources/input_bad_file.xml"))
                .when()
                .post(harryKartApp)
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    @DisplayName("Test bad input with no powerups")
    void testBadInputRequestBlankPowerup() {
        given()
                .header("Content-Type", ContentType.XML)
                .body(new File("src/main/resources/input_bad_with_no_powerup.xml"))
                .when()
                .post(harryKartApp)
                .then()
                .assertThat()
                .statusCode(400);
    }

}
