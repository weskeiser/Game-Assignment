package Game.Components.GameCharacters.Remains;

import java.util.List;

import Game.Components.Exceptions.LootException;
import Game.Components.GameCharacters.Hero.Hero;
import Game.Components.GameCharacters.Interfaces.Attacker;
import Game.Components.GameCharacters.Interfaces.LootableRemains;
import Game.Components.Items.Item;

public class Remains implements LootableRemains {
  List<Item> lootableItems;
  private Attacker defeatedBy = null;

  @Override
  public void showLoot(Hero investigator) {
    if (investigator != defeatedBy) {
      System.out.println(LootException.Messages.NOT_YOURS);
      return;
    }

    // TODO: Implement
    System.out.println("Dev definitely needs coffee");

  }

  @Override
  public Attacker getDefeator() {
    return defeatedBy;
  }

  @Override
  public Item takeItem(Item lootItem) throws LootException {
    boolean taken = lootableItems.remove(lootItem);
    if (!taken)
      throw new LootException(LootException.Messages.NOT_FOUND);
    return lootItem;
  }

  public Remains(List<Item> lootableItems, Attacker defeatedBy) {
    this.lootableItems = lootableItems;
    this.defeatedBy = defeatedBy;
  }
}
