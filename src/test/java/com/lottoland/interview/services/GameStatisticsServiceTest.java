package com.lottoland.interview.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;

import com.lottoland.interview.models.GameStatistics;
import com.lottoland.interview.services.impl.GameStatisticsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GameStatisticsServiceTest {

  @TestConfiguration
  static class GameStatisticsServiceImplTestContextConfiguration {

    @Bean
    public GameStatisticsService employeeService() {
      return new GameStatisticsServiceImpl();
    }
    @Bean
    public GameStatistics gameStatistics() {
      return new GameStatistics();
    }
  }

  @Autowired
  private GameStatisticsService gameStatisticsService;
  @Autowired
  private GameStatistics gameStatistics;

  @Test
  @DirtiesContext(methodMode = BEFORE_METHOD)
  public void addNewPlayedGameTestPlayer1Wins(){
    gameStatisticsService.addNewPlayedGame(true,false);
    assertThat(gameStatistics.getTotalRoundsPlayed()).isEqualTo(1);
    assertThat(gameStatistics.getTotalWinsForFirstPlayers()).isEqualTo(1);
    assertThat(gameStatistics.getTotalWinsForSecondPlayers()).isEqualTo(0);
    assertThat(gameStatistics.getTotalDraws()).isEqualTo(0);
  }

  @Test
  @DirtiesContext(methodMode = BEFORE_METHOD)
  public void addNewPlayedGameTestPlayer2Wins(){
    gameStatisticsService.addNewPlayedGame(false,true);
    assertThat(gameStatistics.getTotalRoundsPlayed()).isEqualTo(1);
    assertThat(gameStatistics.getTotalWinsForFirstPlayers()).isEqualTo(0);
    assertThat(gameStatistics.getTotalWinsForSecondPlayers()).isEqualTo(1);
    assertThat(gameStatistics.getTotalDraws()).isEqualTo(0);
  }

  @Test
  @DirtiesContext(methodMode = BEFORE_METHOD)
  public void addNewPlayedGameTestDraw(){
    gameStatisticsService.addNewPlayedGame(false,false);
    assertThat(gameStatistics.getTotalRoundsPlayed()).isEqualTo(1);
    assertThat(gameStatistics.getTotalWinsForFirstPlayers()).isEqualTo(0);
    assertThat(gameStatistics.getTotalWinsForSecondPlayers()).isEqualTo(0);
    assertThat(gameStatistics.getTotalDraws()).isEqualTo(1);
  }
}
