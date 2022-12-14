package Game.Components.GameCharacters.Interfaces;

import Game.Components.GameCharacters.Remains.Remains;

public interface Defender {

  int getLevel();

  Remains surrenderValuables();

  double getHealth();

  boolean defend(double hit, CharacterAttribute attackType);

  boolean takeDamage(int damage, Attacker foe);

  public void receiveFinalBlow(Attacker defeator);

}
