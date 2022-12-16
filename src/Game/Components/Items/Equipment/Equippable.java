package Game.Components.Items.Equipment;

import Game.Components.Items.Item;

public interface Equippable extends Item {

  EquipmentType getEquipmentType();

  EquipmentSlot getEquipmentSlot();

  int getLevelRequirement();

  void printLevelRequirement();

}
