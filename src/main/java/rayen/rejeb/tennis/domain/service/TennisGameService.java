package rayen.rejeb.tennis.domain.service;

import static rayen.rejeb.tennis.domain.utils.StringUtils.isBlank;

import rayen.rejeb.tennis.domain.api.GameApi;
import rayen.rejeb.tennis.domain.exception.DomainException;
import rayen.rejeb.tennis.domain.model.Game;
import rayen.rejeb.tennis.domain.model.Player;
import rayen.rejeb.tennis.domain.model.PlayerEnum;
import rayen.rejeb.tennis.domain.spi.ScoreDisplaySpi;
import java.util.List;
import java.util.function.Predicate;

public class TennisGameService implements GameApi {

  private final ScoreDisplaySpi displaySpi;

  public TennisGameService(ScoreDisplaySpi displaySpi) {
    this.displaySpi = displaySpi;
  }

  @Override
  public Game createNewGame(PlayerEnum labelOne, PlayerEnum labelTwo) {
    Player playerOne = new Player(labelOne);
    Player playerTwo = new Player(labelTwo);
    return new Game(playerOne, playerTwo);
  }

  @Override
  public void start(Game game, String gameScoreString) {
    Player playerOne = game.playerOne();
    Player playerTwo = game.playerTwo();
    List<Character> rally = processGameRally(
        gameScoreString,
        playerOne.getNameCharacter(),
        playerTwo.getNameCharacter()
    );
    rally
        .stream()
        .map(game::playPoint)
        .takeWhile(shouldDisplay())
        .forEach(winner -> displaySpi.displayScore(playerOne, playerTwo));
  }

  @Override
  public void designateWinner(Game game) {
    Player winner = game.designateWinner();
    displaySpi.displayWinner(winner);
  }

  private List<Character> processGameRally(String rally, Character... playerLabels) {
    if (isBlank(rally)) {
      throw new DomainException("Game Score cannot be empty", getClass().getSimpleName());
    }
    List<Character> labels = List.of(playerLabels);
    List<Character> rallyList = rally
        .chars()
        .mapToObj(value -> (char) value)
        .toList();
    boolean containsInvalidLabels = rallyList
        .stream()
        .distinct()
        .anyMatch(value -> !labels.contains(value));
    if (containsInvalidLabels) {
      throw new DomainException(
          "Game rally contains invalid labels, different than " + labels,
          getClass().getSimpleName()
      );
    }
    return rallyList;
  }

  private Predicate<Player> shouldDisplay() {
    return player -> !player.hasWon();
  }

}
