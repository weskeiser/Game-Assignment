package Game.Components.GameCharacters.Interfaces;

import Game.Components.Exceptions.LootException;
import Game.Components.Items.Item;

public interface LootableRemains {
  Item takeItem(Item item) throws LootException;

  void showLoot(GameCharacter investigator);

  Attacker getDefeator();

}
