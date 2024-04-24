package rayen.rejeb.tennis.domain.model;

public class Player {

  private final PlayerEnum name;
  private ScoreEnum score;

  public Player(PlayerEnum player) {
    this.name = player;
    this.score = ScoreEnum.ZERO;
  }

  public Character getNameCharacter() {
    return name.getPlayerName();
  }

  public String getActualScore() {
    return score.getActualScore();
  }

  public void winPoint() {
    this.score = this.score.getNextScore();
  }

  public void loseAdvantage() {
    this.score = ScoreEnum.FORTY;
  }

  public void winGame() {
    this.score = ScoreEnum.WIN;
  }

  public Boolean hasWon() {
    return this.score == ScoreEnum.WIN;
  }

  public boolean hasFortyPoints() {
    return this.score == ScoreEnum.FORTY;
  }

  public boolean hasAdvantage() {
    return this.score == ScoreEnum.ADVANTAGE;
  }

  public boolean isCalled(Character character) {
    return this.name.getPlayerName() == character;
  }

}
