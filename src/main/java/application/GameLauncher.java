package application;

import static domain.model.PlayerEnum.PLAYER_A;
import static domain.model.PlayerEnum.PLAYER_B;

import domain.api.GameApi;
import domain.model.Game;

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
