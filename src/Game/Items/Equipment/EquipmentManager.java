package Game.Items.Equipment;

import java.util.*;

import Game.Exceptions.*;
import Game.GameCharacters.Hero.HeroType;
import Game.Items.Item;
import Game.Items.Equipment.Armor.ArmorType;
import Game.Items.Equipment.Weapon.*;
import Game.Items.Inventory.InventoryManager;

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

          unEquip(EquipmentSlot.WEAPON, equippedItems, inventory);
          equippedItems.put(EquipmentSlot.WEAPON, equipment);

        default:
          if (!((EnumSet<ArmorType>) heroType.getValidArmorTypes()).contains(equipment.getEquipmentType())) {
            throw new InvalidArmorException(InvalidArmorException.Messages.WRONG_TYPE);
          }

          unEquip(equipment.getEquipmentSlot(), equippedItems, inventory);
          equippedItems.put(equipment.getEquipmentSlot(), equipment);

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