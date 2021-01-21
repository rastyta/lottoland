package com.lottoland.interview.services.impl;

import static org.slf4j.LoggerFactory.getLogger;

import com.lottoland.interview.models.GameStatistics;
import com.lottoland.interview.services.GameStatisticsService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class GameStatisticsServiceImpl implements GameStatisticsService {

  private static final Logger LOGGER = getLogger(GameStatisticsServiceImpl.class);

  @Autowired
  private GameStatistics gameStatistics;

  /**
   * {@inheritDoc}
   */
  @Override
  @Async("threadPoolTaskExecutor")
  public void addNewPlayedGame (boolean player1Wins, boolean player2Wins){
    LOGGER.debug("New Game added to statistics");
    gameStatistics.addNewPlayedGame(player1Wins,player2Wins);
  }
}
