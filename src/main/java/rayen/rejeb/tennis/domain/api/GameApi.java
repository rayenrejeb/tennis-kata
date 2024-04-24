package rayen.rejeb.tennis.domain.api;

import rayen.rejeb.tennis.domain.model.Game;
import rayen.rejeb.tennis.domain.model.PlayerEnum;

public interface GameApi {

  Game createNewGame(PlayerEnum labelOne, PlayerEnum labelTwo);

  void start(Game game, String gameScoreString);

  void designateWinner(Game game);
}
