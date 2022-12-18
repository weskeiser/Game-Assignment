package main.Game.GameAction.Combat;

import main.Game.Components.GameCharacters.Interfaces.Attacker;
import main.Game.Components.GameCharacters.Interfaces.Defender;
import main.Game.GameAction.GameTask;

public interface CombatTask extends GameTask {
  static boolean ALIVE = true;
  static boolean DEAD = false;

  void newAttack(Attacker attacker, Defender defender);

  void disengageAttacker(Attacker attacker);

  boolean performAttack(Attacker attacker, Defender defender);
}
