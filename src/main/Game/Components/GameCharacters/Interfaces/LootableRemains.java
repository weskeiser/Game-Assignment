package main.Game.Components.GameCharacters.Interfaces;

import main.Game.Components.Exceptions.LootException;
import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.Items.Item;

public interface LootableRemains {
  Item takeItem(Item item) throws LootException;

  void showLoot(Hero investigator);

  Attacker getDefeator();

}
