package Game.GameCharacters;

import Game.Exceptions.LootException;
import Game.Items.Lootable;

public interface Remains {
  Lootable takeItem(Lootable item) throws LootException;

  void showLoot(GameCharacter investigator);

  GameCharacter getDefeator();

}
