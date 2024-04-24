package application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import rayen.rejeb.tennis.application.GameLauncher;
import rayen.rejeb.tennis.application.GameLauncherApi;
import rayen.rejeb.tennis.domain.api.GameApi;
import org.junit.jupiter.api.Test;

class GameLauncherTest {

  @Test
  void should_run_game() {
    // Given
    String rally = "ABABAB";
    GameApi game = mock(GameApi.class);
    GameLauncherApi gameLauncher = new GameLauncher(game);
    doNothing().when(game).start(any(), any());
    doNothing().when(game).designateWinner(any());
    // When
    gameLauncher.play(rally);
    // Then
    verify(game, times(1)).start(any(), any());
    verify(game, times(1)).designateWinner(any());

  }

}
