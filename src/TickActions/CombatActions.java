package TickActions;

import java.util.List;

import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Hero.HeroType;
import Game.GameCharacters.Interfaces.*;

public interface CombatActions {
  default void combat(Attacker attacker, Defender defender) {
    double maxHit = attacker.getMaxHit();

    // randomise actual hit

    double actualHit = maxHit;

    if (((GameCharacter) attacker).getCharacterType() instanceof HeroType) {
      ((Hero) attacker).gainExperience((int) actualHit);
    }

    defender.defend(maxHit, attacker);

    if (defender.getHealth() <= 0) {
      // kill(defender);
    }
  };

  default void kill(Defender killed, List<Defender> killedList) {
  }

}
