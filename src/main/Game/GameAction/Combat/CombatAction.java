package main.Game.GameAction.Combat;

import java.util.*;

import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.GameCharacters.Interfaces.Attacker;
import main.Game.Components.GameCharacters.Interfaces.Defender;

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

  public void disengageAttacker(Attacker attacker) {
    attackers.remove(attacker);
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

    var defenderSurvived = defender.takeDamage((int) randomisedMaxHit, attacker);

    if (attacker instanceof Hero) {
      attacker.gainExperience(randomisedMaxHit);
    }

    //

    System.out.println(defender.getHealth());

    if (!defenderSurvived) {
      attacker.receiveLootAccess(defender.surrenderValuables());

      attacker.setCurrentlyAttacking(null);
      defender.setCurrentlyAttacking(null);
      return DEAD;
    }

    return ALIVE;
  }

  public void run() {
    var attackIterator = attackers.iterator();

    while (attackIterator.hasNext()) {
      var attacker = attackIterator.next();

      Optional<Defender> optionalDefender = attacker.getCurrentlyAttacking();

      optionalDefender.ifPresent((defender) -> {

        // Attackers -> After death: Remove concurrent attackers
        if (defender.getHealth() <= 0) {
          attacker.setCurrentlyAttacking(null);
          attackIterator.remove();
        }

        // Actual attack
        var aliveStatus = performAttack(attacker, defender);

        if (aliveStatus == DEAD) {

          // Attackers -> After death: Remove attacker
          attackIterator.remove();

          // Attackers -> After death: Remove defender
          attackers.remove((Attacker) defender);
        }
      });
    }
  }

  private CombatAction(CombatTasksBuilder builder) {
    this.attackers = builder.attackers;
  }

  public static class CombatTasksBuilder {
    private HashSet<Attacker> attackers;

    // Default
    public CombatTasksBuilder() {
      this.attackers = new HashSet<Attacker>();
    }

    // For use in instances
    public CombatTasksBuilder setAttackers(HashSet<Attacker> attackers) {
      this.attackers = attackers;
      return this;
    }

    public CombatAction build() {
      return new CombatAction(this);
    }
  }
}
