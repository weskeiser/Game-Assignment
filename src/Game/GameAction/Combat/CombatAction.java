package Game.GameAction.Combat;

import java.util.*;

import Game.Components.GameCharacters.Hero.HeroType;
import Game.Components.GameCharacters.Interfaces.*;

public class CombatAction extends TimerTask implements CombatTask {

  private Map<Attacker, Defender> engagements = new HashMap<>();
  private Map<Attacker, Defender> fatalities = new HashMap<>();

  public void newAttack(Attacker attacker, Defender defender) {
    engagements.put(attacker, defender);
  }

  public void disengageAttack(Attacker attacker, Defender defender) {
    engagements.remove(attacker, defender);
  }

  public double randomiseHit(double min, double maxHit) {
    // Considering logic change
    double max = maxHit <= 1 ? 1.99 : maxHit;

    // Randomise hit
    double firstRoll = (Math.random() * (max - min)) + min;

    // If upper 25%, roll again. Makes high hits more special.
    if ((firstRoll > max * 0.75)) {
      return (Math.random() * (max - min)) + min;
    }

    return firstRoll;
  }

  public void performAttack(Attacker attacker, Defender defender) {
    // Attacker -> Return if attacker is on cooldown
    if (attacker.decrementIfAttackCooldown())
      return;

    // Attacker -> Set attack cooldown
    attacker.setAttackCooldown(GAME_TICKS - attacker.getAttackSpeed());

    // Attacker -> Get attack type
    CharacterAttribute attackAttribute = attacker.getAttackAttribute();

    // Defender -> Attempt to deflect, cancel attack if success
    boolean deflected = defender.defend(attackAttribute);
    if (deflected)
      return;

    // Attacker -> Get max hit and randomise it
    double maxHit = attacker.getMaxHit();
    double randomisedHit = randomiseHit(0, maxHit);

    // Defender -> Take damage
    boolean alive = defender.takeDamage((int) randomisedHit, attacker);

    // Attacker -> If opponent killed, attacker gets loot access
    // Defender -> Clear valuables
    if (!alive) {
      attacker.receiveLootAccess(defender.surrenderValuables());
      fatalities.put(attacker, defender);
    }

    // Attacker -> If not NPC, gain experience
    if (((GameCharacter) attacker).getCharacterType() instanceof HeroType) {
      attacker.gainExperience(randomisedHit);
    }

  }

  public void run() {
    engagements.forEach(this::performAttack);

    fatalities.forEach(this::disengageAttack);
  }

  private CombatAction(CombatTasksBuilder builder) {
    this.engagements = builder.engagements;
  }

  public static class CombatTasksBuilder {
    private Map<Attacker, Defender> engagements;

    public CombatTasksBuilder() {
      this.engagements = new HashMap<>();
    }

    // For use in instances
    public CombatTasksBuilder setEngagements(Map<Attacker, Defender> engagements) {
      this.engagements = engagements;
      return this;
    }

    public CombatAction build() {
      return new CombatAction(this);
    }
  }
}
