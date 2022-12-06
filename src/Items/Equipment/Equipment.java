package Items.Equipment;

import Items.Item;

public interface Equipment extends Item {

  <E extends Enum<E>> E getEquipmentType();

  EquipmentSlot getEquipmentSlot();

  int getLevelRequirement();

  void printLevelRequirement();

}
