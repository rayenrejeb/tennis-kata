package infrastructure;

import domain.model.Player;
import domain.spi.ScoreDisplaySpi;

public class ScoreDisplay implements ScoreDisplaySpi {

  private static final String PLAYERS_DISPLAY_SEPARATOR = " | ";
  private static final String PLAYER_SCORE_MESSAGE = "Player %-1s : %-2s";

  @Override
  public void displayScore(Player playerOne, Player playerTwo) {

    if (playerOne == null || playerTwo == null) {
      throw new IllegalArgumentException("displayScore requires at least two players");
    }
    String scorePlayerOne = PLAYER_SCORE_MESSAGE.formatted(
        playerOne.getName().getPlayerName(),
        playerOne.getScore().getActualScore()
    );
    String scorePlayerTwo = PLAYER_SCORE_MESSAGE.formatted(
        playerTwo.getName().getPlayerName(),
        playerTwo.getScore().getActualScore()
    );
    String messageToDisplay = scorePlayerOne + PLAYERS_DISPLAY_SEPARATOR + scorePlayerTwo;

    System.out.println(messageToDisplay);
  }

  @Override
  public void displayWinner(Player winner) {
    Character winnerName = winner.getName().getPlayerName();
    String message = "Player %s wins the game".formatted(winnerName);
    System.out.println(message);
  }

}