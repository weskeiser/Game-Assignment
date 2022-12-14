package Game.GameAction.Combat;

import Game.Components.GameCharacters.Interfaces.Attacker;
import Game.Components.GameCharacters.Interfaces.Defender;
import Game.GameAction.GameTask;

public interface CombatTask extends GameTask {

  void newAttack(Attacker attacker, Defender defender);

  void disengageAttack(Attacker attacker, Defender defender);

  void performAttack(Attacker attacker, Defender defender);
}
