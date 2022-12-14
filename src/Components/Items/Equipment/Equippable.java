package Components.Items.Equipment;

import Components.Items.Item;

public interface Equippable extends Item {

  EquipmentType getEquipmentType();

  EquipmentSlot getEquipmentSlot();

  int getLevelRequirement();

  void printLevelRequirement();

}
