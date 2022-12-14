package Game.GameActions;

import java.util.*;

import Game.Components.GameCharacters.Hero.HeroType;
import Game.Components.GameCharacters.Interfaces.*;

public class CombatTasks extends TimerTask implements CombatActions {

  private Map<Attacker, Defender> engagements = new HashMap<>();
  private Map<Attacker, Defender> fatalities = new HashMap<>();

  public void newAttack(Attacker attacker, Defender defender) {
    engagements.put(attacker, defender);
  }

  public void disengageAttack(Attacker attacker, Defender defender) {
    engagements.remove(attacker, defender);
  }

  public double randomiseHit(double min, double maxHit) {
    double max = maxHit <= 1 ? 1.99 : maxHit;

    double firstRoll = (Math.random() * (max - min)) + min;

    // If upper 25%, roll again
    if ((firstRoll > max * 0.75)) {
      return (Math.random() * (max - min)) + min;
    }

    return firstRoll;
  }

  public void performAttack(Attacker attacker, Defender defender) {
    attacker.setAttackCooldown(GAME_TICKS - attacker.getAttackSpeed());

    double maxHit = attacker.getMaxHit();

    double randomisedHit = randomiseHit(0, maxHit);

    CharacterAttribute attackType = attacker.getCurrentAttackType();

    boolean deflected = defender.defend(randomisedHit, attackType);

    if (deflected)
      return;

    boolean alive = defender.takeDamage((int) randomisedHit, attacker);

    if (!alive) {
      attacker.addRemains(defender.getRemains());
      fatalities.put(attacker, defender);
    }

    if (((GameCharacter) attacker).getCharacterType() instanceof HeroType) {
      attacker.gainExperience(randomisedHit);
    }

  }

  public void run() {
    engagements.forEach((attacker, defender) -> {
      if (!attacker.decrementIfAttackCooldown())
        performAttack(attacker, defender);

      System.out.println(defender.getHealth());
    });

    fatalities.forEach((attacker, defender) -> {
      disengageAttack(attacker, defender);
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
