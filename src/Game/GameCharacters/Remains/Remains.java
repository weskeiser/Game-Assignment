package Game.GameCharacters.Remains;

import java.util.List;

import Game.Exceptions.LootException;
import Game.GameCharacters.Interfaces.GameCharacter;
import Game.GameCharacters.Interfaces.LootableRemains;
import Game.Items.LootableItem;

public class Remains implements LootableRemains {
  List<LootableItem> lootableItems;
  private GameCharacter defeatedBy = null;

  @Override
  public void showLoot(GameCharacter investigator) {
    if (investigator != defeatedBy) {
      System.out.println(LootException.Messages.NOT_YOURS);
      return;
    }

    System.out.println(lootableItems);
  }

  @Override
  public GameCharacter getDefeator() {
    return defeatedBy;
  }

  @Override
  public LootableItem takeItem(LootableItem lootItem) throws LootException {
    boolean taken = lootableItems.remove(lootItem);
    if (!taken)
      throw new LootException(LootException.Messages.NOT_FOUND);
    return lootItem;
  }

  public Remains(List<LootableItem> lootableItems, GameCharacter defeatedBy) {
    this.lootableItems = lootableItems;
    this.defeatedBy = defeatedBy;
  }
}
