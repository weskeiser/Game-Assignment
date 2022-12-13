package CharacterStatus;

import java.util.*;

import Game.GameCharacters.Interfaces.CombatManager;
import Game.GameCharacters.Interfaces.GameCharacter;

public class CharacterStatus extends TimerTask {

  GameCharacter player;
  List<CombatManager> attackedBy = new ArrayList<>();

  public void addAttacker(CombatManager attacker) {
    attackedBy.add(attacker);
  }

  public void removeAttacker(CombatManager attacker) {
    attackedBy.remove(attacker);
  }

  public void run() {
    System.out.println("hi");
  }

  private CharacterStatus(PlayerBuilder builder) {
    this.player = builder.player;
  }

  public static class PlayerBuilder {
    private GameCharacter player;

    public PlayerBuilder(GameCharacter player) {
      this.player = player;
    }

    public CharacterStatus build() {
      return new CharacterStatus(this);
    }
  }
}
