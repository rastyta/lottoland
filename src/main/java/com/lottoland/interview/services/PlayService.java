package com.lottoland.interview.services;

import com.lottoland.interview.models.Game;
import com.lottoland.interview.models.GameResults;
import com.lottoland.interview.models.Games;
import com.lottoland.interview.models.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *This service is in charge of executing
 * the different rounds that each user performs in their game.
 */
public interface PlayService {

  /**
   * This method executes a new round in the user's game
   * and starts a new asynchronous entry in the statistics.
   * @param games Games object model mapped per each user session.
   */
  void play(Games games);

  /**
   * This method remove all rounds for the user's game.
   * @param games Games object model mapped per each user session.
   */
  void clear(Games games);
}
