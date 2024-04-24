package rayen.rejeb.tennis.domain.spi;

import rayen.rejeb.tennis.domain.model.Player;

public interface ScoreDisplaySpi {

  void displayScore(Player playerOne, Player playerTwo);

  void displayWinner(Player winner);
}
