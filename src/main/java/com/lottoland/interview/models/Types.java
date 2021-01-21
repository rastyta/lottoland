package com.lottoland.interview.models;

import java.util.Random;

/**
 * Game type enum, it represents all possible shapes of a game.
 */
public enum Types {
  ROCK, PAPER, SCISSORS;

  /**
   * Pick a random Type of the enum.
   * @return a random Type.
   */
  public static Types getRandomType() {
    Random random = new Random();
    return values()[random.nextInt(values().length)];
  }
}