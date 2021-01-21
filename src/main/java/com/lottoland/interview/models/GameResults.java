package com.lottoland.interview.models;

/**
 * Game result enum, it represents all possible outcomes of a game,
 * with the literal value that we want to display in the view.
 */
public enum GameResults {
  Win1("Player 1 Win"), Win2("Player 2 Win"), DRAW("Draw");

  private final String value;

  GameResults(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
