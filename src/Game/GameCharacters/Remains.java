package Game.GameCharacters;

import Game.Exceptions.LootException;
import Game.Items.LootableItem;

public interface Remains {
  LootableItem takeItem(LootableItem item) throws LootException;

  void showLoot(GameCharacter investigator);

  GameCharacter getDefeator();

}
