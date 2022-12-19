package main.Game.Components.GameCharacters.Remains;

import java.util.List;

import main.Game.Components.Exceptions.LootException;
import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.GameCharacters.Interfaces.Attacker;
import main.Game.Components.GameCharacters.Interfaces.LootableRemains;
import main.Game.Components.Items.GameItem;

public class Remains implements LootableRemains {
  List<GameItem> lootableItems;
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
  public GameItem takeItem(GameItem lootItem) throws LootException {
    boolean taken = lootableItems.remove(lootItem);
    if (!taken)
      throw new LootException(LootException.Messages.NOT_FOUND);
    return lootItem;
  }

  public Remains(List<GameItem> lootableItems, Attacker defeatedBy) {
    this.lootableItems = lootableItems;
    this.defeatedBy = defeatedBy;
  }
}
