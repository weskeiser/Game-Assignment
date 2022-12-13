package Game.GameCharacters.Interfaces;

import Game.GameCharacters.Remains.Remains;

public interface Defender {

  int getLevel();

  Remains getRemains();

  double getHealth();

  int defend(double maxHit, Attacker foe);

  void takeDamage(double damage);

  public void receiveFinalBlow(Attacker defeator);

}
