package Game.Items.Equipment;

import Game.Items.Item;

public interface Equippable extends Item {

  EquipmentType getEquipmentType();

  EquipmentSlot getEquipmentSlot();

  int getLevelRequirement();

  void printLevelRequirement();

}
