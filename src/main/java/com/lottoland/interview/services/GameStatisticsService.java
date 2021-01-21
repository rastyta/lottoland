package com.lottoland.interview.services;

import com.lottoland.interview.models.GameStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service that is in charge of managing and updating
 * the statistics of all the games played by all users.
 */
public interface GameStatisticsService {

  /**
   * This method add new game to the statistics counts.
   * Note that this is an asynchronous method,
   * which allows us to update the game statistics
   * without penalizing the user experience when playing.
   *
   * @param player1Wins true if player one win.
   * @param player2Wins true if player two win.
   */
  void addNewPlayedGame (boolean player1Wins, boolean player2Wins);
}
