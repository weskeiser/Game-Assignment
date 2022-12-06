package GameCharacters.Actions;

import GameCharacters.GameCharacter;

public interface CombatManager {
  void attack(GameCharacter foe);

  void defendYourself(double maxHit);
}