package Interfaces;

import Equipment.Weapon;

public interface EquipmentManager extends ItemManager {

  void wield(Weapon weapon);

  static boolean equip(Equipment item) {
    return true;
  };

  static boolean unEquip(Equipment item) {
    return true;
  };

  static boolean displayItems() {
    return true;
  };

  static boolean displayArmorAttributes() {
    return true;
  };

}