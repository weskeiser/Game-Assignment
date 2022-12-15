package Game.GameAction.Combat;

import java.util.*;

import Game.Components.GameCharacters.Hero.HeroType;
import Game.Components.GameCharacters.Interfaces.*;

public class CombatAction extends TimerTask implements CombatTask {

  private HashSet<Attacker> attackers = new HashSet<>();

  public void newAttack(Attacker attacker, Defender defender) {
    attackers.add(attacker);
    attacker.setCurrentlyAttacking(defender);

    // Defender -> Automatically attack back if not actively attacking someone else.
    Optional<Defender> defenderCurrentlyAttacking = defender.getCurrentlyAttacking();

    if (defenderCurrentlyAttacking.isEmpty()) {

      attackers.add((Attacker) defender);

      defender.setCurrentlyAttacking((Defender) attacker);
    }
  }

  public void disengageAttack(Attacker attacker, Defender defender) {
    // engagements.remove(attacker, defender);
  }

  public double randomiseHit(double maxHit) {
    double max = maxHit <= 1 ? 1.99 : maxHit;

    double firstRoll = (Math.random() * (max - 0)) + 0;

    // If upper 25%, roll again to make high hits more special.
    if ((firstRoll > max * 0.75)) {
      return (Math.random() * (max - 0)) + 0;
    }

    return firstRoll;
  }

  public boolean performAttack(Attacker attacker, Defender defender) {
    if (attacker.decrementIfAttackCooldown())
      return ALIVE;

    attacker.setAttackCooldown(GAME_TICKS - attacker.getAttackSpeed());

    //

    if (defender.defend(attacker.getAttackAttribute()))
      return ALIVE;

    var randomisedMaxHit = randomiseHit(attacker.getMaxHit());

    boolean defenderSurvived = defender.takeDamage((int) randomisedMaxHit, attacker);

    System.out.println(defender.getHealth());

    if (((GameCharacter) attacker).getCharacterType() instanceof HeroType) {
      attacker.gainExperience(randomisedMaxHit);
    }

    //

    if (!defenderSurvived) {
      attacker.receiveLootAccess(defender.surrenderValuables());

      attacker.setCurrentlyAttacking(null);
      defender.setCurrentlyAttacking(null);
      return DEAD;
      // engagements.remove(attacker, defender);
      // engagements.remove((Attacker) defender, (Defender) attacker);
    }

    return ALIVE;
  }

  public void run() {
    var attackIterator = attackers.iterator();

    while (attackIterator.hasNext()) {
      var attacker = attackIterator.next();
      System.out.println(((GameCharacter) attacker).getName());

      Optional<Defender> optionalDefender = attacker.getCurrentlyAttacking();

      optionalDefender.ifPresent((defender) -> {
        // Remove concurrent attackers
        if (defender.getHealth() <= 0)
          attackIterator.remove();

        boolean aliveStatus = performAttack(attacker, defender);

        if (aliveStatus == DEAD) {
          // Remove attacker
          attackIterator.remove();
          // Remove defender
          attackers.remove((Attacker) defender);
        }
      });
    }
  }

  private CombatAction(CombatTasksBuilder builder) {
    // this.engagements = builder.engagements;
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
