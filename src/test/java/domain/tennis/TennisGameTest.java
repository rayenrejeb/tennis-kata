package domain.tennis;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import rayen.rejeb.tennis.domain.api.GameApi;
import rayen.rejeb.tennis.domain.exception.DomainException;
import rayen.rejeb.tennis.domain.model.Game;
import rayen.rejeb.tennis.domain.model.Player;
import rayen.rejeb.tennis.domain.model.PlayerEnum;
import rayen.rejeb.tennis.domain.service.TennisGameService;
import rayen.rejeb.tennis.domain.spi.ScoreDisplaySpi;
import rayen.rejeb.tennis.infrastructure.ScoreDisplay;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TennisGameTest {

  GameApi api;
  Game game;
  Player playerOne;
  Player playerTwo;
  ScoreDisplaySpi displaySpi;

  @BeforeEach
  void set_up() {
    playerOne = mock(Player.class);
    playerTwo = mock(Player.class);
    game = mock(Game.class);
    displaySpi = mock(ScoreDisplay.class);
    when(game.playerOne()).thenReturn(playerOne);
    when(game.playerTwo()).thenReturn(playerTwo);
    when(playerOne.getNameCharacter()).thenReturn(PlayerEnum.PLAYER_A.getPlayerName());
    when(playerTwo.getNameCharacter()).thenReturn(PlayerEnum.PLAYER_B.getPlayerName());
    api = new TennisGameService(displaySpi);
  }

  @Test
  void should_create_new_game() {
    // Given
    // When
    var result = api.createNewGame(PlayerEnum.PLAYER_A, PlayerEnum.PLAYER_B);
    // Then
    assertTrue(result instanceof Game);
  }

  @ParameterizedTest
  @MethodSource("invalidGameRally")
  void should_throw_exception_on_invalid_input(String ignore, String rally) {
    assertThrows(DomainException.class, () -> api.start(game, rally));
  }

  @Test
  void should_start_game() {
    // Given
    String rally = "ABABAB";
    Game game = mock(Game.class);
    when(game.playerOne()).thenReturn(playerOne);
    when(game.playerTwo()).thenReturn(playerTwo);
    when(game.playPoint(any())).thenReturn(this.playerOne);
    // When
    api.start(game, rally);
    // Then
    verify(game, times(6)).playPoint(any());
  }

  @Test
  void should_designate_winner() {
    // Given
    when(game.designateWinner()).thenReturn(playerOne);
    doNothing().when(displaySpi).displayWinner(any());
    // When
    api.designateWinner(game);
    // Then
    verify(game, only()).designateWinner();
    verify(displaySpi, only()).displayWinner(any());
  }

  @Test
  void should_throw_exception_on_designate_winner_when_not_found() {
    // Given
    when(game.designateWinner()).thenThrow(mock(DomainException.class));
    // When
    // Then
    assertThrows(DomainException.class, () -> api.designateWinner(game));
  }

  public static Stream<Arguments> invalidGameRally() {
    return Stream.of(
        Arguments.of("should not validate empty rally", ""),
        Arguments.of("should not validate space", " "),
        Arguments.of("should not validate rally with wrong players", "ABC", true)
    );
  }

}
