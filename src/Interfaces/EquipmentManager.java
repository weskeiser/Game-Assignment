package Interfaces;

public interface EquipmentManager extends ItemManager {

  void equip(Equipment item);

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