package TickActions;

import Components.GameCharacters.Interfaces.Attacker;
import Components.GameCharacters.Interfaces.Defender;

public interface CombatActions extends GameActions {

  void newAttack(Attacker attacker, Defender defender);

  void disengageAttack(Attacker attacker, Defender defender);

  void performAttack(Attacker attacker, Defender defender);

}
