package domain.model;

public class Player {

  private final PlayerEnum name;
  private ScoreEnum score;

  public Player(PlayerEnum player) {
    this.name = player;
    this.score = ScoreEnum.ZERO;
  }

  public PlayerEnum getName() {
    return name;
  }

  public ScoreEnum getScore() {
    return score;
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


}
