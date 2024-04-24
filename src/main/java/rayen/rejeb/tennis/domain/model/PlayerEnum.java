package rayen.rejeb.tennis.domain.model;

public enum PlayerEnum {

  PLAYER_A('A'), PLAYER_B('B');

  private final Character playerName;

  PlayerEnum(Character playerName) {
    this.playerName = playerName;
  }

  public Character getPlayerName() {
    return playerName;
  }

}
