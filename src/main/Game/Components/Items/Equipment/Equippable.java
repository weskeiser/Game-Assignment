package main.Game.Components.Items.Equipment;

import main.Game.Components.Items.GameItem;

public interface Equippable extends GameItem {

  EquipmentType getEquipmentType();

  EquipmentSlot getEquipmentSlot();

  int getLevelRequirement();

  void printLevelRequirement();

}
