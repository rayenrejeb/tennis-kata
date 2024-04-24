package rayen.rejeb.tennis.domain.model;

import rayen.rejeb.tennis.domain.exception.DomainException;

public record Game(Player playerOne, Player playerTwo) {

  public Player playPoint(Character label) {
    Player pointWinner = playerOne.isCalled(label) ? playerOne : playerTwo;
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
    if (winner.hasAdvantage() && opponent.hasAdvantage()) {
      winner.loseAdvantage();
      opponent.loseAdvantage();
      return winner;
    }
    if (winner.hasAdvantage() && !opponent.hasFortyPoints()) {
      winner.winGame();
    }
    return winner;
  }

}
