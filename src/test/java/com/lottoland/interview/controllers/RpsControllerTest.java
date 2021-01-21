package com.lottoland.interview.controllers;

import com.lottoland.interview.models.GameResults;
import com.lottoland.interview.models.Types;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;

/**
 * Test class for all RpsController end points
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RpsControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void homeTest() {
    String template = this.restTemplate.getForObject("http://localhost:" + port + "/",
        String.class);
    assertThat(template).contains("Number of rounds played");
    assertThat(template).contains("0");
    assertThat(template).doesNotContain(Types.ROCK.name());
  }

  @Test
  public void playTest() {
    String template = this.restTemplate.getForObject("http://localhost:" + port + "/play",
        String.class);
    assertThat(template).contains("Number of rounds played");
    assertThat(template).contains("1</span>");
    assertThat(template).contains(Types.ROCK.name());
    assertTrue(
        template.contains(GameResults.Win1.getValue()) ||
            template.contains(GameResults.Win2.getValue()) ||
            template.contains(GameResults.DRAW.getValue())
    );
  }

  @Test
  public void playTestTwoRoundsOnePlayer() throws URISyntaxException {
    ResponseEntity<String> firstRoundPlayed = restTemplate.exchange(
        new URI("http://localhost:" + port + "/play"), HttpMethod.GET, null, String.class);
    String jSession = Objects.requireNonNull(firstRoundPlayed.getHeaders().get("Set-Cookie")).get(0);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Cookie", jSession);
    HttpEntity<?> entity = new HttpEntity<>(headers);
    ResponseEntity<String> secondRoundPlayed = restTemplate.exchange(
        new URI("http://localhost:" + port + "/play"), HttpMethod.GET, entity, String.class);
    String templateSecondRoundPlayed = secondRoundPlayed.getBody();
    assertThat(templateSecondRoundPlayed).contains("Number of rounds played");
    assertThat(templateSecondRoundPlayed).contains("2</span>");
  }

  @Test
  public void playTestTwoRoundsOnePlayerAndOneRoundAnotherPlayer() throws URISyntaxException {
    ResponseEntity<String> firstRoundPlayer1 = restTemplate.exchange(
        new URI("http://localhost:" + port + "/play"), HttpMethod.GET, null, String.class);
    String jSession = Objects.requireNonNull(firstRoundPlayer1.getHeaders().get("Set-Cookie")).get(0);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Cookie", jSession);
    HttpEntity<?> entity = new HttpEntity<>(headers);
    ResponseEntity<String> secondRoundPlayer1 = restTemplate.exchange(
        new URI("http://localhost:" + port + "/play"), HttpMethod.GET, entity, String.class);
    String templateSecondRoundPlayer1 = secondRoundPlayer1.getBody();
    assertThat(templateSecondRoundPlayer1).contains("Number of rounds played");
    assertThat(templateSecondRoundPlayer1).contains("2</span>");

    ResponseEntity<String> firstRoundPlayer2 = restTemplate.exchange(
        new URI("http://localhost:" + port + "/play"), HttpMethod.GET, null, String.class);
    String templateFirstRoundPlayer1 = firstRoundPlayer2.getBody();
    assertThat(templateFirstRoundPlayer1).contains("Number of rounds played");
    assertThat(templateFirstRoundPlayer1).contains("1</span>");
  }

  @Test
  @DirtiesContext(methodMode = BEFORE_METHOD)
  public void statisticsTest() {
    String template = this.restTemplate.getForObject("http://localhost:" + port + "/statistics",
        String.class);
    assertThat(template).contains("<tr>\n"
        + "        <th>Total Rounds Played</th>\n"
        + "        <th>Total Wins For First Players</th>\n"
        + "        <th>Total Wins For Second Players</th>\n"
        + "        <th>Total Draws</th>\n"
        + "      </tr>\n"
        + "      <tr>\n"
        + "        <td><span>0</span></td>\n"
        + "        <td><span>0</span></td>\n"
        + "        <td><span>0</span></td>\n"
        + "        <td><span>0</span></td>\n"
        + "      </tr>");
  }

  @Test
  @DirtiesContext(methodMode = BEFORE_METHOD)
  public void statisticsPlayer1TwoRoundsPlayer2OneRoundTest() throws Exception {
    ResponseEntity<String> firstRoundPlayer1 = restTemplate.exchange(
        new URI("http://localhost:" + port + "/play"), HttpMethod.GET, null, String.class);
    String jSession = Objects.requireNonNull(firstRoundPlayer1.getHeaders().get("Set-Cookie")).get(0);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Cookie", jSession);
    HttpEntity<?> entity = new HttpEntity<>(headers);
    ResponseEntity<String> secondRoundPlayer1 = restTemplate.exchange(
        new URI("http://localhost:" + port + "/play"), HttpMethod.GET, entity, String.class);

    ResponseEntity<String> firstRoundPlayer2 = restTemplate.exchange(
        new URI("http://localhost:" + port + "/play"), HttpMethod.GET, null, String.class);

    String template = this.restTemplate.getForObject("http://localhost:" + port + "/statistics",
        String.class);
    assertThat(template).contains("<tr>\n"
        + "        <th>Total Rounds Played</th>\n"
        + "        <th>Total Wins For First Players</th>\n"
        + "        <th>Total Wins For Second Players</th>\n"
        + "        <th>Total Draws</th>\n"
        + "      </tr>\n"
        + "      <tr>\n"
        + "        <td><span>3</span></td>");
  }
}