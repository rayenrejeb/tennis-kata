package infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import rayen.rejeb.tennis.domain.model.PlayerEnum;
import rayen.rejeb.tennis.domain.model.ScoreEnum;
import rayen.rejeb.tennis.domain.spi.ScoreDisplaySpi;
import rayen.rejeb.tennis.domain.model.Player;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import rayen.rejeb.tennis.infrastructure.ScoreDisplay;

class ScoreDisplayTest {

  ScoreDisplaySpi scoreDisplay = new ScoreDisplay();
  ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void should_display_players_score() {
    // Given
    Player playerOne = mock(Player.class);
    Player playerTwo = mock(Player.class);
    when(playerOne.getNameCharacter()).thenReturn(PlayerEnum.PLAYER_A.getPlayerName());
    when(playerTwo.getNameCharacter()).thenReturn(PlayerEnum.PLAYER_B.getPlayerName());
    when(playerOne.getActualScore()).thenReturn(ScoreEnum.FIFTEEN.getActualScore());
    when(playerTwo.getActualScore()).thenReturn(ScoreEnum.ZERO.getActualScore());
    // When
    scoreDisplay.displayScore(playerOne, playerTwo);
    // Then
    assertEquals("Player A : 15 | Player B : 0", outputStreamCaptor.toString().trim());
  }

  @Test
  void should_designate_winner() {
    // Given
    Player playerOne = mock(Player.class);
    when(playerOne.getNameCharacter()).thenReturn(PlayerEnum.PLAYER_A.getPlayerName());
    // When
    scoreDisplay.displayWinner(playerOne);
    // Then
    assertEquals("[32mPlayer A wins the game", outputStreamCaptor.toString().trim());
  }

  @Test
  void should_throw_exception_on_display_when_players_number_less_than_two() {
    // Given
    System.setOut(new PrintStream(outputStreamCaptor));
    Player playerOne = mock(Player.class);
    // When
    // Then
    assertThrows(IllegalArgumentException.class, () -> scoreDisplay.displayScore(playerOne, null));
  }

}
