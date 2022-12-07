package Game.GameCharacters.Actions;

import Game.GameCharacters.GameCharacter;

public interface CombatManager {
  void attack(GameCharacter foe);

  void defendYourself(double maxHit);
}