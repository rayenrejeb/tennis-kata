package domain.model;

import domain.exception.DomainException;
import java.util.Arrays;

public enum PlayerEnum {

  PLAYER_A('A'), PLAYER_B('B');

  private final Character playerName;

  PlayerEnum(Character playerName) {
    this.playerName = playerName;
  }

  public Character getPlayerName() {
    return playerName;
  }

  public static PlayerEnum valueOf(Character character) {
    if (character == null) {
      throw new DomainException(
          "Player name cannot be null",
          PlayerEnum.class.getSimpleName()
      );
    }
    return Arrays.stream(values())
        .filter(value -> value.playerName.equals(character))
        .findFirst()
        .orElseThrow(() -> new DomainException(
            character + " is not a recognizable player",
            PlayerEnum.class.getSimpleName()
        ));
  }

}
