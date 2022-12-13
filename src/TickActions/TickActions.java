package TickActions;

import java.util.*;

import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Hero.HeroType;
import Game.GameCharacters.Interfaces.*;

public class TickActions extends TimerTask implements CombatActions {

  Map<Attacker, Defender> attacks = new HashMap<>();

  public void addAttack(Attacker attacker, Defender defender) {
    attacks.put(attacker, defender);
  }

  public void removeAttack(Attacker attacker, Defender defender) {
    attacks.remove(attacker, defender);
  }

  public void combat(Attacker attacker, Defender defender) {
    double maxHit = attacker.getMaxHit();

    // randomise actual hit

    double actualHit = maxHit;

    if (((GameCharacter) attacker).getCharacterType() instanceof HeroType) {
      ((Hero) attacker).gainExperience((int) actualHit);
    }

    defender.defend(maxHit, attacker);

    if (defender.getHealth() <= 0) {
      // kill(defender);
    }
  }

  public void run() {
    System.out.println("hi");

    attacks.forEach((attacker, defender) -> {
      combat(attacker, defender);

      System.out.println(defender.getHealth());
    });
  }

  private TickActions() {
  }

  public static class TickActionsBuilder {

    public TickActionsBuilder() {
    }

    public TickActions build() {
      return new TickActions();
    }
  }
}
