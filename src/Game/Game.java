package Game;

import Game.Exceptions.LootException;
import Game.GameCharacters.GameCharacter;
import Game.GameCharacters.Remains;
import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Hero.HeroType;
import Game.Items.Item;
import Game.Items.Lootable;

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

  static void loot(Remains remains, GameCharacter looter, Lootable lootItem) {
    try {
      // check inventory space

      if (looter != remains.getDefeator())
        throw new LootException(LootException.Messages.NOT_YOURS);

      remains.takeItem(lootItem);
      looter.addToInventory((Item) lootItem);
    } catch (Throwable err) {
      System.out.println(err.getMessage());
    }
  }

  static void attack(GameCharacter defender, GameCharacter attacker) {
    double maxHit = attacker.getMaxHit();
    defender.defend(maxHit, attacker);
  };

}
