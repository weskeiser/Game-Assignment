package Game.GameCharacters.Interfaces;

import java.util.EnumMap;
import java.util.List;

import Game.Items.Item;
import Game.Items.LootableItem;
import Game.Items.Equipment.EquipmentSlot;
import Game.Items.Equipment.Equippable;

public interface Defender {

  int getLevel();

  double getHealth();

  void defend(double maxHit, Attacker foe);

  void takeDamage(double damage);

  public void receiveFinalBlow(Attacker defeator);

  default void receiveFinalBlow(Attacker defeator, double health, Attacker defeatedBy,
      EnumMap<EquipmentSlot, Equippable> equippedItems, List<LootableItem> remains, List<Item> inventory) {

    health = 0;
    defeatedBy = defeator;

    equippedItems.values().forEach((item) -> remains.add((LootableItem) item));
    equippedItems.clear();

    inventory.forEach((item) -> remains.add((LootableItem) item));
    inventory.clear();
  };

}
