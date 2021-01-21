package com.lottoland.interview.services.impl;

import com.lottoland.interview.models.GameStatistics;
import com.lottoland.interview.services.GameStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class GameStatisticsServiceImpl implements GameStatisticsService {

  @Autowired
  private GameStatistics gameStatistics;

  /**
   * {@inheritDoc}
   */
  @Override
  @Async("threadPoolTaskExecutor")
  public void addNewPlayedGame (boolean player1Wins, boolean player2Wins){
    gameStatistics.addNewPlayedGame(player1Wins,player2Wins);
  }
}
