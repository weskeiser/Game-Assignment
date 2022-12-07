package Game.Items.Equipment;

import Game.Items.Item;

public interface Equipment extends Item {

  EquipmentType getEquipmentType();

  EquipmentSlot getEquipmentSlot();

  double getDamageMultiplier();

  int getLevelRequirement();

  void printLevelRequirement();

}
