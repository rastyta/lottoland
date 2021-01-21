package com.lottoland.interview.services.impl;

import com.lottoland.interview.models.Game;
import com.lottoland.interview.models.GameResults;
import com.lottoland.interview.models.Games;
import com.lottoland.interview.models.Types;
import com.lottoland.interview.services.GameStatisticsService;
import com.lottoland.interview.services.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *This service is in charge of executing
 * the different rounds that each user performs in their game.
 */
@Service
public class PlayServiceImpl implements PlayService {

  @Autowired
  private GameStatisticsService gameStatisticsService;

  /**
   * This method executes a new round in the user's game
   * and starts a new asynchronous entry in the statistics.
   * @param games Games object model mapped per each user session.
   */
  public void play(Games games) {
    Game game = new Game();
    boolean player1Wins = false;
    boolean player2Wins = false;
    Types player1Type = Types.getRandomType();
    game.setPlayer1Type(player1Type.name());
    switch (player1Type){
      case ROCK:
        game.setResult(GameResults.DRAW.getValue());
        break;
      case PAPER:
        game.setResult(GameResults.Win1.getValue());
        player1Wins = true;
        break;
      case SCISSORS:
        game.setResult(GameResults.Win2.getValue());
        player2Wins = true;
        break;
    }
    gameStatisticsService.addNewPlayedGame(player1Wins,player2Wins);
    games.addGame(game);
  }

  /**
   * This method remove all rounds for the user's game.
   * @param games Games object model mapped per each user session.
   */
  public void clear(Games games){
    games.clear();
  }
}
