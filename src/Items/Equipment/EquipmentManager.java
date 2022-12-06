package Items.Equipment;

import java.util.*;
import Exceptions.*;
import Items.Equipment.Weapon.*;
import Items.Item;
import Items.Inventory.InventoryManager;
import GameCharacters.Hero.HeroType;

public interface EquipmentManager extends InventoryManager {

  void equip(Equipment equipment);

  default void equip(Equipment equipment,
      List<Item> inventory,
      EnumMap<EquipmentSlot, Equipment> equippedItems,
      int heroLevel,
      HeroType heroType)
      throws Throwable {

    try {
      if (!inventory.remove(equipment)) {
        throw new InventoryException(InventoryException.Messages.NOT_FOUND);
      }

      if (equipment.getLevelRequirement() > heroLevel) {
        throw new InvalidEquipmentException(InvalidEquipmentException.Messages.LEVEL_REQUIREMENT);
      }

      switch (equipment.getEquipmentSlot()) {
        case WEAPON:

          if (!((EnumSet<WeaponType>) heroType.getValidWeaponTypes()).contains(equipment.getEquipmentType())) {
            throw new InvalidWeaponException(InvalidWeaponException.Messages.WRONG_TYPE);
          }

          // Replace with unequip method
          Item unwielded = equippedItems.remove(EquipmentSlot.WEAPON);
          if (unwielded instanceof Weapon) {
            addToInventory(inventory, (Weapon) unwielded);
          }
          // Replace with unequip method ^^^^

          equippedItems.put(EquipmentSlot.WEAPON, equipment);
        default:
      }

    } catch (Throwable err) {
      throw err;
    }
  };

  default void unEquip(EquipmentSlot equipmentSlot,
      EnumMap<EquipmentSlot, Equipment> equippedItems,
      List<Item> inventory)
      throws Throwable {

    try {
      Equipment unEquipped = equippedItems.remove(equipmentSlot);

      if (unEquipped instanceof Equipment) {
        addToInventory(inventory, unEquipped);
        System.out.println(unEquipped.getName() + " was unequipped and added to the inventory.");
      } else {
        throw new InvalidEquipmentException(InvalidEquipmentException.Messages.SLOT_EMPTY);
      }

    } catch (Throwable err) {
      System.out.println(err.getMessage());
    }
  };

  static boolean displayItems() {
    return true;
  };

  static boolean displayArmorAttributes() {
    return true;
  };

}