package rayen.rejeb.tennis.infrastructure;

import rayen.rejeb.tennis.domain.model.Player;
import rayen.rejeb.tennis.domain.spi.ScoreDisplaySpi;

public class ScoreDisplay implements ScoreDisplaySpi {

  private static final String PLAYERS_DISPLAY_SEPARATOR = " | ";
  private static final String PLAYER_SCORE_MESSAGE = "Player %-1s : %-2s";
  private static final String WINNER_SCORE_MESSAGE = "\u001B[32mPlayer %s wins the game";

  @Override
  public void displayScore(Player playerOne, Player playerTwo) {
    if (playerOne == null || playerTwo == null) {
      throw new IllegalArgumentException("players are mandatory for display");
    }
    String messageToDisplay = String.join(
        PLAYERS_DISPLAY_SEPARATOR,
        formatPlayerScore(playerOne),
        formatPlayerScore(playerTwo)
    );
    System.out.println(messageToDisplay);
  }

  @Override
  public void displayWinner(Player winner) {
    Character winnerName = winner.getNameCharacter();
    String message = WINNER_SCORE_MESSAGE.formatted(winnerName);
    System.out.println(message);
  }

  private String formatPlayerScore(Player player) {
    Character playerLabel = player.getNameCharacter();
    String score = player.getActualScore();
    return PLAYER_SCORE_MESSAGE.formatted(playerLabel, score);
  }

}