package Game.GameCharacters.Remains;

import java.util.List;

import Game.Exceptions.LootException;
import Game.GameCharacters.Interfaces.*;
import Game.Items.LootableItem;

public class Remains implements LootableRemains {
  List<LootableItem> lootableItems;
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
  public LootableItem takeItem(LootableItem lootItem) throws LootException {
    boolean taken = lootableItems.remove(lootItem);
    if (!taken)
      throw new LootException(LootException.Messages.NOT_FOUND);
    return lootItem;
  }

  public Remains(List<LootableItem> lootableItems, Attacker defeatedBy) {
    this.lootableItems = lootableItems;
    this.defeatedBy = defeatedBy;
  }
}
