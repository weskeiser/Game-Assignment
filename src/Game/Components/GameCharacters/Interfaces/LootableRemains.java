package Game.Components.GameCharacters.Interfaces;

import Game.Components.Exceptions.LootException;
import Game.Components.GameCharacters.Hero.Hero;
import Game.Components.Items.Item;

public interface LootableRemains {
  Item takeItem(Item item) throws LootException;

  void showLoot(Hero investigator);

  Attacker getDefeator();

}
