package Game.GameCharacters.Interfaces;

import Game.Exceptions.LootException;
import Game.Items.Item;

public interface LootableRemains {
  Item takeItem(Item item) throws LootException;

  void showLoot(GameCharacter investigator);

  Attacker getDefeator();

}
