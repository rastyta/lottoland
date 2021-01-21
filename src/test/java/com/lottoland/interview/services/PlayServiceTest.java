package com.lottoland.interview.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;

import com.lottoland.interview.models.GameStatistics;
import com.lottoland.interview.models.Games;
import com.lottoland.interview.models.Types;
import com.lottoland.interview.services.impl.GameStatisticsServiceImpl;
import com.lottoland.interview.services.impl.PlayServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PlayServiceTest {

  @TestConfiguration
  static class playServiceImplTestContextConfiguration {
    @Bean
    public PlayService playService() {
      return new PlayServiceImpl();
    }
    @Bean
    public Games games() {
      return new Games();
    }
    @Bean
    public GameStatisticsService gameStatisticsService() {
      return new GameStatisticsServiceImpl();
    }
    @Bean
    public GameStatistics gameStatistics() {
      return new GameStatistics();
    }
  }

  @Autowired
  PlayService playService;
  @Autowired
  Games games;
  @Autowired
  GameStatisticsService gameStatisticsService;
  @Autowired
  private GameStatistics gameStatistics;

  @Test
  @DirtiesContext(methodMode = BEFORE_METHOD)
  public void playTest(){
    playService.play(games);
    assertThat(games.getNumberOfRounds()).isEqualTo(1);
    assertThat(games.getRounds().size()).isEqualTo(1);
  }

  @Test
  @DirtiesContext(methodMode = BEFORE_METHOD)
  public void playTwoRoundsTest(){
    playService.play(games);
    playService.play(games);
    assertThat(games.getNumberOfRounds()).isEqualTo(2);
    assertThat(games.getRounds().size()).isEqualTo(2);
  }

  @Test
  @DirtiesContext(methodMode = BEFORE_METHOD)
  public void clearTwoRoundsTest(){
    playService.play(games);
    playService.play(games);
    assertThat(games.getNumberOfRounds()).isEqualTo(2);
    assertThat(games.getRounds().size()).isEqualTo(2);
    playService.clear(games);
    assertThat(games.getNumberOfRounds()).isEqualTo(0);
    assertThat(games.getRounds().size()).isEqualTo(0);
  }
}
