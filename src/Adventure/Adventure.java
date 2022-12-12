package Adventure;

import java.util.*;

import Game.GameCharacters.GameCharacter;

public class Adventure extends TimerTask {

  GameCharacter player;
  List<GameCharacter> attackedBy = new ArrayList<>();

  public void addAttacker(GameCharacter attacker) {
    attackedBy.add(attacker);
  }

  public void removeAttacker(GameCharacter attacker) {
    attackedBy.remove(attacker);
  }

  public void run() {
    System.out.println("hi");
  }

  public Adventure(GameCharacter player) {
    this.player = player;
  }
}
