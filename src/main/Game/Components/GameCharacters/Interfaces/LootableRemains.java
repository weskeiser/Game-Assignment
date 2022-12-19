package main.Game.Components.GameCharacters.Interfaces;

import main.Game.Components.Exceptions.LootException;
import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.Items.GameItem;

public interface LootableRemains {
  GameItem takeItem(GameItem item) throws LootException;

  void showLoot(Hero investigator);

  Attacker getDefeator();

}
