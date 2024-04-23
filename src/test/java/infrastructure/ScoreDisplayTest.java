package infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import domain.model.PlayerEnum;
import domain.model.ScoreEnum;
import domain.spi.ScoreDisplaySpi;
import domain.model.Player;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

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
    when(playerOne.getName()).thenReturn(PlayerEnum.PLAYER_A);
    when(playerTwo.getName()).thenReturn(PlayerEnum.PLAYER_B);
    when(playerOne.getScore()).thenReturn(ScoreEnum.FIFTEEN);
    when(playerTwo.getScore()).thenReturn(ScoreEnum.ZERO);
    // When
    scoreDisplay.displayScore(playerOne, playerTwo);
    // Then
    assertEquals("Player A : 15 | Player B : 0", outputStreamCaptor.toString().trim());
  }

  @Test
  void should_designate_winner() {
    // Given
    Player playerOne = mock(Player.class);
    when(playerOne.getName()).thenReturn(PlayerEnum.PLAYER_A);
    // When
    scoreDisplay.displayWinner(playerOne);
    // Then
    assertEquals("Player A wins the game", outputStreamCaptor.toString().trim());
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
