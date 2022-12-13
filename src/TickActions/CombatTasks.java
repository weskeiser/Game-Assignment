package TickActions;

import java.util.*;

import Game.GameCharacters.Hero.HeroType;
import Game.GameCharacters.Interfaces.*;
import Game.GameCharacters.Remains.Remains;

public class CombatTasks extends TimerTask implements CombatActions {

  private Map<Attacker, Defender> engagements = new HashMap<>();

  public void newAttack(Attacker attacker, Defender defender) {
    engagements.put(attacker, defender);
  }

  public void disengageAttack(Attacker attacker, Defender defender) {
    engagements.remove(attacker, defender);
  }

  public void combat(Attacker attacker, Defender defender) {
    double maxHit = attacker.getMaxHit();

    // randomise actual hit

    double randomisedHit = maxHit;

    int actualHit = defender.defend(randomisedHit, attacker);

    if (((GameCharacter) attacker).getCharacterType() instanceof HeroType) {
      attacker.gainExperience(actualHit);
    }

    if (defender.getHealth() <= 0) {
      disengageAttack(attacker, defender);

      Remains remains = defender.getRemains();

      attacker.addRemains(remains);

      // kill
    }
  }

  public void run() {
    System.out.println("hi");

    engagements.forEach((attacker, defender) -> {
      combat(attacker, defender);

      System.out.println(defender.getHealth());
    });
  }

  private CombatTasks(CombatTasksBuilder builder) {
    this.engagements = builder.engagements;
  }

  public static class CombatTasksBuilder {
    private Map<Attacker, Defender> engagements;

    public CombatTasksBuilder() {
      this.engagements = new HashMap<>();
    }

    public CombatTasksBuilder setEngagements(Map<Attacker, Defender> engagements) {
      this.engagements = engagements;
      return this;
    }

    public CombatTasks build() {
      return new CombatTasks(this);
    }
  }
}
