package IT;


import static org.junit.jupiter.api.Assertions.assertEquals;

import application.GameLauncher;
import application.GameLauncherApi;
import domain.api.GameApi;
import domain.service.TennisGameService;
import domain.spi.ScoreDisplaySpi;
import infrastructure.ScoreDisplay;
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
        Player A wins the game
        """;
    // When
    tennisGameLauncher.play(gameRally);
    // Then
    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }

}
