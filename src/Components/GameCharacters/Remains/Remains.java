package Components.GameCharacters.Remains;

import java.util.List;

import Components.Exceptions.LootException;
import Components.GameCharacters.Interfaces.*;
import Components.Items.Item;

public class Remains implements LootableRemains {
  List<Item> lootableItems;
  private Attacker defeatedBy = null;

  @Override
  public void showLoot(GameCharacter investigator) {
    if (investigator != defeatedBy) {
      System.out.println(LootException.Messages.NOT_YOURS);
      return;
    }

    System.out.println(lootableItems);
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
