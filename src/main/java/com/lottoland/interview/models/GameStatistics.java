package com.lottoland.interview.models;

import org.springframework.stereotype.Component;

/**
 * Game Statistics, it represent a count of all rounds played for all users.
 */
@Component
public class GameStatistics {
  private int totalRoundsPlayed, totalWinsForFirstPlayers, totalWinsForSecondPlayers, totalDraws;

  /**
   * This method add new game to the statistics.
   * Note that it is a synchronized method, this guarantees consistency between the different sessions.
   * @param player1Wins true if player one win.
   * @param player2Wins true if player two win.
   */
  public synchronized void addNewPlayedGame(boolean player1Wins, boolean player2Wins){
    totalRoundsPlayed+=1;
    if(player1Wins)
      totalWinsForFirstPlayers+=1;
    else if(player2Wins)
      totalWinsForSecondPlayers+=1;
    else
      totalDraws+=1;
  }
  public int getTotalRoundsPlayed() {
    return totalRoundsPlayed;
  }

  public void setTotalRoundsPlayed(int totalRoundsPlayed) {
    this.totalRoundsPlayed = totalRoundsPlayed;
  }

  public int getTotalWinsForFirstPlayers() {
    return totalWinsForFirstPlayers;
  }

  public void setTotalWinsForFirstPlayers(int totalWinsForFirstPlayers) {
    this.totalWinsForFirstPlayers = totalWinsForFirstPlayers;
  }

  public int getTotalWinsForSecondPlayers() {
    return totalWinsForSecondPlayers;
  }

  public void setTotalWinsForSecondPlayers(int totalWinsForSecondPlayers) {
    this.totalWinsForSecondPlayers = totalWinsForSecondPlayers;
  }

  public int getTotalDraws() {
    return totalDraws;
  }

  public void setTotalDraws(int totalDraws) {
    this.totalDraws = totalDraws;
  }
}
