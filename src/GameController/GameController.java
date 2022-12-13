package GameController;

import Game.Exceptions.InventoryException;
import Game.Exceptions.LootException;
import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Hero.HeroType;
import Game.GameCharacters.Interfaces.LootableRemains;
import Game.Items.Item;

public class GameController {

  public Hero newWarrior(String name) {
    return new Hero.HeroBuilder(name, HeroType.WARRIOR).build();
  }

  public Hero newRogue(String name) {
    return new Hero.HeroBuilder(name, HeroType.ROGUE).build();
  }

  public Hero newRanger(String name) {
    return new Hero.HeroBuilder(name, HeroType.RANGER).build();
  }

  public Hero newMage(String name) {
    return new Hero.HeroBuilder(name, HeroType.MAGE).build();
  }

  public void lootRemains(LootableRemains remains, Hero looter, Item lootItem) {
    try {
      if (looter.getFreeInventorySlots() <= 0)
        throw new LootException(LootException.Messages.FULL_INVENTORY);

      if (looter != remains.getDefeator())
        throw new LootException(LootException.Messages.NOT_YOURS);

      remains.takeItem(lootItem);
      looter.addToInventory((Item) lootItem);

    } catch (LootException err) {
      System.out.println(err.getMessage());

    } catch (InventoryException err) {
      System.out.println(err.getMessage());
    }
  }

}
