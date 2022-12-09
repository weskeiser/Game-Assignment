package Game.Items.Equipment;

import java.util.*;

import Game.Exceptions.*;
import Game.GameCharacters.Hero.HeroType;
import Game.Items.Item;
import Game.Items.Equipment.Armor.*;
import Game.Items.Equipment.Weapon.*;

public interface EquipmentManager {

  default void equip(Equipment equipment,
      List<Item> inventory,
      EnumMap<EquipmentSlot, Equipment> equippedItems,
      int heroLevel,
      HeroType heroType)
      throws Throwable {

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

        try {
          unEquip(EquipmentSlot.WEAPON, equippedItems, inventory);
        } catch (Throwable err) {
        }

        equippedItems.put(EquipmentSlot.WEAPON, (Weapon) equipment);

        System.out.println("You equip a " + equipment.getName() + " from your inventory.");

        break;

      // All armor slots
      default:
        if (!((EnumSet<ArmorType>) heroType.getValidArmorTypes()).contains(equipment.getEquipmentType())) {
          throw new InvalidArmorException(InvalidArmorException.Messages.WRONG_TYPE);
        }

        try {
          unEquip(equipment.getEquipmentSlot(), equippedItems, inventory);
        } catch (Throwable err) {
        }

        equippedItems.put(equipment.getEquipmentSlot(), (Armor) equipment);
    }

  };

  default void unEquip(EquipmentSlot equipmentSlot,
      EnumMap<EquipmentSlot, Equipment> equippedItems,
      List<Item> inventory)
      throws Throwable {

    Equipment unEquipped = equippedItems.get(equipmentSlot);

    if (unEquipped == null)
      throw new InvalidEquipmentException(InvalidEquipmentException.Messages.SLOT_EMPTY);

    if (inventory.size() >= 15) {
      throw new InventoryException(InventoryException.Messages.INVENTORY_FULL);
    }

    equippedItems.remove(equipmentSlot);

    if (unEquipped instanceof Weapon) {
      inventory.add((Weapon) unEquipped);
    } else if (unEquipped instanceof Armor) {

      inventory.add((Armor) unEquipped);
    }

    System.out.println(unEquipped.getName() + " was unequipped and added to the inventory.");
  };

  // static boolean displayItems() {
  // return true;
  // };

  // static boolean displayArmorAttributes() {
  // return true;
  // };

}