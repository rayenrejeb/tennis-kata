package domain.model;

public enum ScoreEnum {

  WIN("WIN"),
  ADVANTAGE("A"),
  FORTY("40"),
  THIRTY("30"),
  FIFTEEN("15"),
  ZERO("0");

  private final String actualScore;

  ScoreEnum(String actualScore) {
    this.actualScore = actualScore;
  }

  public String getActualScore() {
    return actualScore;
  }

  public ScoreEnum getNextScore() {
    return switch (this) {
      case WIN, ADVANTAGE -> WIN;
      case FORTY -> ADVANTAGE;
      case THIRTY -> FORTY;
      case FIFTEEN -> THIRTY;
      default -> FIFTEEN;
    };
  }

}
