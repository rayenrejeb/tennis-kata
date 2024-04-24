package rayen.rejeb.tennis.application;

import static rayen.rejeb.tennis.domain.model.PlayerEnum.PLAYER_A;
import static rayen.rejeb.tennis.domain.model.PlayerEnum.PLAYER_B;

import rayen.rejeb.tennis.domain.api.GameApi;
import rayen.rejeb.tennis.domain.model.Game;

public class GameLauncher implements GameLauncherApi {

  private final GameApi api;

  public GameLauncher(GameApi api) {
    this.api = api;
  }

  @Override
  public void play(String gameRally) {
    Game game = api.createNewGame(PLAYER_A, PLAYER_B);
    api.start(game, gameRally);
    api.designateWinner(game);
  }

}
