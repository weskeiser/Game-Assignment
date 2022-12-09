package Game;

import Game.Exceptions.LootException;
import Game.GameCharacters.*;
import Game.GameCharacters.Hero.*;
import Game.Items.*;

public interface Game {

  static Hero newWarrior(String name) {
    return new Hero.HeroBuilder(name, HeroType.WARRIOR).build();
  }

  static Hero newRogue(String name) {
    return new Hero.HeroBuilder(name, HeroType.ROGUE).build();
  }

  static Hero newRanger(String name) {
    return new Hero.HeroBuilder(name, HeroType.RANGER).build();
  }

  static Hero newMage(String name) {
    return new Hero.HeroBuilder(name, HeroType.MAGE).build();
  }

  static void loot(Remains remains, Hero looter, Lootable lootItem) {
    try {
      if (looter.getFreeInventorySlots() <= 0)
        throw new LootException(LootException.Messages.FULL_INVENTORY);

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

    // randomise actual hit

    int actualHit = (int) maxHit;

    if (attacker.getCharacterType() instanceof HeroType) {
      ((Hero) attacker).gainExperience(actualHit);
    }

    defender.defend(maxHit, attacker);
  };

}
