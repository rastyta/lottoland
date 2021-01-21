package com.lottoland.interview.models;

/**
 * Game object, it represents a round in the game.
 */
public class Game {
  private String player1Type;
  private final String player2Type = Types.ROCK.name();
  private String result;

  public String getPlayer1Type() {
    return player1Type;
  }

  public void setPlayer1Type(String player1Type) {
    this.player1Type = player1Type;
  }

  public String getPlayer2Type() {
    return player2Type;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
