package Items.Equipment;

import Items.Item;
import Items.Equipment.Weapon.WeaponTypes;

public interface Equipment extends Item {
  int getLevelRequirement();

  EquipmentSlots getEquipmentSlot();

  Enum getEquipmentType();
}
