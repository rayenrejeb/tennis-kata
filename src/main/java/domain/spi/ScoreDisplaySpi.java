package domain.spi;

import domain.model.Player;

public interface ScoreDisplaySpi {

  void displayScore(Player playerOne, Player playerTwo);

  void displayWinner(Player winner);
}
