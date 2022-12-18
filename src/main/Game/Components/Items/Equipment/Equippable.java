package main.Game.Components.Items.Equipment;

import main.Game.Components.Items.Item;

public interface Equippable extends Item {

  EquipmentType getEquipmentType();

  EquipmentSlot getEquipmentSlot();

  int getLevelRequirement();

  void printLevelRequirement();

}
