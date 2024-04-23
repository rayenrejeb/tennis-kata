package domain.api;

import domain.model.Game;
import domain.model.PlayerEnum;

public interface GameApi {

  Game createNewGame(PlayerEnum labelOne, PlayerEnum labelTwo);

  void start(Game game, String gameScoreString);

  void designateWinner(Game game);
}
