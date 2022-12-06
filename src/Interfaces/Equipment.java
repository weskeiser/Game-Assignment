package Interfaces;

import ENUMS.EquipmentSlots;
import Equipment.Weapon.WeaponTypes;

public interface Equipment extends Item {
  int getLevelRequirement();

  EquipmentSlots getEquipmentSlot();

  Enum getEquipmentType();
}
