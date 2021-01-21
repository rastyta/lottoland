package com.lottoland.interview.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent all rounds played in one game.
 * Note this object is used per each user session.
 */
public class Games {
  private int numberOfRounds = 0;
  List<Game> rounds = new ArrayList<>();

  /**
   * This method add a new round.
   * @param game Game played
   */
  public void addGame(Game game){
    numberOfRounds += 1;
    rounds.add(game);
  }

  /**
   * This method remove all rounds.
   */
  public void clear(){
    rounds.clear();
    numberOfRounds=0;
  }

  public int getNumberOfRounds() {
    return numberOfRounds;
  }

  public void setNumberOfRounds(int numberOfRounds) {
    this.numberOfRounds = numberOfRounds;
  }

  public List<Game> getRounds() {
    return rounds;
  }

  public void setRounds(List<Game> rounds) {
    this.rounds = rounds;
  }
}
