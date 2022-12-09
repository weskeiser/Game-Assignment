package Game.Items.Equipment;

import Game.Items.Item;

public interface Equipment<T> extends Item {

  T getEquipmentType();

  EquipmentSlot getEquipmentSlot();

  // double getDamageMultiplier();

  int getLevelRequirement();

  void printLevelRequirement();

}
