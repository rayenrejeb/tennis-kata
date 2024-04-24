package IT;


import static org.junit.jupiter.api.Assertions.assertEquals;

import rayen.rejeb.tennis.application.GameLauncher;
import rayen.rejeb.tennis.application.GameLauncherApi;
import rayen.rejeb.tennis.domain.api.GameApi;
import rayen.rejeb.tennis.domain.service.TennisGameService;
import rayen.rejeb.tennis.domain.spi.ScoreDisplaySpi;
import rayen.rejeb.tennis.infrastructure.ScoreDisplay;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class ITTennisGameTest {

  ScoreDisplaySpi displaySpi = new ScoreDisplay();
  GameApi api = new TennisGameService(displaySpi);
  GameLauncherApi tennisGameLauncher = new GameLauncher(api);
  ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @Test
  void should_run_game_rally() {
    // Given
    System.setOut(new PrintStream(outputStreamCaptor));
    String gameRally = "ABABAA";
    String expectedOutput = """
        Player A : 15 | Player B : 0\s
        Player A : 15 | Player B : 15
        Player A : 30 | Player B : 15
        Player A : 30 | Player B : 30
        Player A : 40 | Player B : 30
        [32mPlayer A wins the game
        """;
    // When
    tennisGameLauncher.play(gameRally);
    // Then
    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }

}
