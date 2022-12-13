package Game.GameCharacters.Interfaces;

import Game.GameCharacters.Remains.Remains;

public interface Defender {

  int getLevel();

  Remains getRemains();

  double getHealth();

  boolean defend(double hit);

  boolean takeDamage(int damage, Attacker foe);

  public void receiveFinalBlow(Attacker defeator);

}
