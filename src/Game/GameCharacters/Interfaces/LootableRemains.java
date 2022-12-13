package Game.GameCharacters.Interfaces;

import Game.Exceptions.LootException;
import Game.Items.LootableItem;

public interface LootableRemains {
  LootableItem takeItem(LootableItem item) throws LootException;

  void showLoot(GameCharacter investigator);

  Attacker getDefeator();

}
