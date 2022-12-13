package TickActions;

import Game.GameCharacters.Interfaces.Attacker;
import Game.GameCharacters.Interfaces.Defender;

public interface CombatActions {
  void newAttack(Attacker attacker, Defender defender);

  void disengageAttack(Attacker attacker, Defender defender);

  void combat(Attacker attacker, Defender defender);

}
