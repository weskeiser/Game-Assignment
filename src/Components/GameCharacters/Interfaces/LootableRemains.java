package Components.GameCharacters.Interfaces;

import Components.Exceptions.LootException;
import Components.Items.Item;

public interface LootableRemains {
  Item takeItem(Item item) throws LootException;

  void showLoot(GameCharacter investigator);

  Attacker getDefeator();

}
