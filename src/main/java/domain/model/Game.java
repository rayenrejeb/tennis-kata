package domain.model;

import static domain.model.ScoreEnum.ADVANTAGE;
import static domain.model.ScoreEnum.FORTY;

import domain.exception.DomainException;
import java.util.Map;

public class Game {

  private final Player playerOne;
  private final Player playerTwo;
  private final Map<PlayerEnum, Player> playersMap;

  public Game(
      Player playerOne,
      Player playerTwo) {
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
    this.playersMap = Map.of(playerOne.getName(), playerOne, playerTwo.getName(), playerTwo);
  }

  public Player getPlayerOne() {
    return playerOne;
  }

  public Player getPlayerTwo() {
    return playerTwo;
  }

  public Player playPoint(Character point) {
    PlayerEnum playerLabel = PlayerEnum.valueOf(point);
    Player pointWinner = this.playersMap.get(playerLabel);
    return winPoint(pointWinner);
  }

  public Player designateWinner() {
    if (playerOne.hasWon()) {
      return playerOne;
    }
    if (playerTwo.hasWon()) {
      return playerTwo;
    }
    throw new DomainException("Game not finished yet", getClass().getSimpleName());
  }

  private Player winPoint(Player winner) {
    Player opponent = winner == playerOne ? playerTwo : playerOne;
    winner.winPoint();
    if (winner.getScore() == ADVANTAGE && opponent.getScore() == ADVANTAGE) {
      winner.loseAdvantage();
      opponent.loseAdvantage();
      return winner;
    }
    if (winner.getScore() == ADVANTAGE && opponent.getScore() != FORTY) {
      winner.winGame();
    }
    return winner;
  }

}
