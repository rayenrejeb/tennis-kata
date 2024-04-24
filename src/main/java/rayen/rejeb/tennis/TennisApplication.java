package rayen.rejeb.tennis;

import java.util.Scanner;
import rayen.rejeb.tennis.application.GameLauncher;
import rayen.rejeb.tennis.application.GameLauncherApi;
import rayen.rejeb.tennis.domain.api.GameApi;
import rayen.rejeb.tennis.domain.exception.DomainException;
import rayen.rejeb.tennis.domain.service.TennisGameService;
import rayen.rejeb.tennis.domain.spi.ScoreDisplaySpi;
import rayen.rejeb.tennis.infrastructure.ScoreDisplay;

public class TennisApplication {

  private static final ScoreDisplaySpi displaySpi = new ScoreDisplay();
  private static final GameApi api = new TennisGameService(displaySpi);
  private static final GameLauncherApi tennisGameLauncher = new GameLauncher(api);

  private static final String WELCOME_MESSAGE = """
          \u001B[34m
            _____ _____ _   _ _   _ ___ ____     ____    _    __  __ _____
           |_   _| ____| \\ | | \\ | |_ _/ ___|   / ___|  / \\  |  \\/  | ____|
             | | |  _| |  \\| |  \\| || |\\___ \\  | |  _  / _ \\ | |\\/| |  _|
             | | | |___| |\\  | |\\  || | ___) | | |_| |/ ___ \\| |  | | |___
             |_| |_____|_| \\_|_| \\_|___|____/   \\____/_/   \\_\\_|  |_|_____|
          \u001B[0m                                                    \s
          Available Players : A & B
          Please provide the game rally:
          """;

  private static final String GAME_START_MESSAGE = """
          \u001B[34m
          ------------------------
         |       GAME START       |
          ------------------------
          \u001B[0m
          """;

  public static void main(String[] args) {
    try {
      System.out.println(WELCOME_MESSAGE);
      String input = readInput();
      System.out.println(GAME_START_MESSAGE);
      tennisGameLauncher.play(input);
    } catch (DomainException exception) {
      handleError(exception);
    }
  }

  private static String readInput() {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.next();
    return input;
  }

  private static void handleError(DomainException exception) {
    String errorClass = exception.getRejectedObjectName();
    String errorMessage = exception.getMessage();
    String error = "%s: %s".formatted(errorClass, errorMessage);
    System.out.println("\u001B[31m" + error);
  }

}
