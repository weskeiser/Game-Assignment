package Game;

import Game.GameCharacters.GameCharacter;
import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Hero.HeroType;

public interface Game {

  static GameCharacter newWarrior(String name) {
    return new Hero.HeroBuilder(name, HeroType.WARRIOR).build();
  }

  static GameCharacter newRogue(String name) {
    return new Hero.HeroBuilder(name, HeroType.ROGUE).build();
  }

  static GameCharacter newRanger(String name) {
    return new Hero.HeroBuilder(name, HeroType.RANGER).build();
  }

  static GameCharacter newMage(String name) {
    return new Hero.HeroBuilder(name, HeroType.MAGE).build();
  }

  static void loot() {

  }

}
