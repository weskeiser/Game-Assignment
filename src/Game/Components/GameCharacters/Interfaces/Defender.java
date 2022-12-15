package Game.Components.GameCharacters.Interfaces;

import java.util.Optional;

import Game.Components.GameCharacters.Remains.Remains;

public interface Defender {

  int getLevel();

  Remains surrenderValuables();

  double getHealth();

  Optional<Defender> getCurrentlyAttacking();

  void setCurrentlyAttacking(Defender currentlyAttacking);

  boolean defend(CharacterAttribute attackType);

  boolean takeDamage(int damage, Attacker foe);

  public void receiveFinalBlow(Attacker defeator);

}
