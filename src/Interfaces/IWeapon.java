package Interfaces;

import ENUMS.EquipmentSlots;
import ENUMS.WeaponTypes;

public interface IWeapon extends Equipment {
  public static final EquipmentSlots EquipmentSlot = ENUMS.EquipmentSlots.WEAPON;

  public WeaponTypes getWeaponType();
}