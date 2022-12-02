package Interfaces;

import ENUMS.EquipmentSlot;

public interface IEquipmentManager {

  boolean equip(IItem item);

  boolean unEquip(IItem item);

  boolean displayItems();

  boolean displayArmorAttributes();

}