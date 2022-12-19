package main.Game.Components.GameCharacters.Interfaces;

import main.Game.Components.GameCharacters.Remains.Remains;

public interface Defender extends Combatant {

  int getLevel();

  Remains surrenderValuables();

  double getHealth();

  boolean defend(CharacterAttribute attackType);

  boolean takeDamage(int damage, Attacker foe);

  public void receiveFinalBlow(Attacker defeator);

}
