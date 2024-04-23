import application.GameLauncher;
import application.GameLauncherApi;
import domain.api.GameApi;
import domain.exception.DomainException;
import domain.service.TennisGameService;
import domain.spi.ScoreDisplaySpi;
import infrastructure.ScoreDisplay;
import java.util.Scanner;

public class TennisApplication {

  private static final ScoreDisplaySpi displaySpi = new ScoreDisplay();
  private static final GameApi api = new TennisGameService(displaySpi);
  private static final GameLauncherApi tennisGameLauncher = new GameLauncher(api);

  public static void main(String[] args) {
    try {
      Scanner scanner = new Scanner(System.in);
      String input = scanner.next();
      tennisGameLauncher.play(input);
    } catch (DomainException exception) {
      String error = "%s: %s".formatted(exception.getRejectedObjectName(), exception.getMessage());
      System.out.println(error);
    }
  }

}
