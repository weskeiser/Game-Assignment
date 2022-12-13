package Game.Items.Equipment;

import java.util.*;

import Game.Exceptions.InvalidEquipmentException;
import Game.Exceptions.InvalidEquipmentException.EquipmentErrMessages;
import Game.Exceptions.InventoryException;
import Game.Exceptions.InventoryException.InventoryErrMessages;
import Game.GameCharacters.Hero.HeroType;
import Game.Items.Item;
import Game.Items.Equipment.Armor.Armor;
import Game.Items.Equipment.Armor.ArmorType;
import Game.Items.Equipment.Weapon.Weapon;
import Game.Items.Equipment.Weapon.WeaponType;

public interface EquipmentManager {

  void equip(Equippable equipment) throws InvalidEquipmentException,
      InventoryException;

  void equip(int inventoryIndex) throws InvalidEquipmentException, InventoryException;

  default void equip(Equippable equipment,
      List<Item> inventory,
      EnumMap<EquipmentSlot, Equippable> equippedItems,
      int heroLevel,
      HeroType heroType)
      throws InvalidEquipmentException, InventoryException {

    if (!inventory.remove(equipment)) {
      throw new InventoryException(InventoryErrMessages.NOT_FOUND);
    }

    if (equipment.getLevelRequirement() > heroLevel) {
      throw new InvalidEquipmentException(EquipmentErrMessages.LEVEL_REQUIREMENT);
    }

    switch (equipment.getEquipmentSlot()) {
      case WEAPON:
        if (!((EnumSet<WeaponType>) heroType.getValidWeaponTypes()).contains(equipment.getEquipmentType())) {
          throw new InvalidEquipmentException(EquipmentErrMessages.WRONG_TYPE);
        }

        try {
          unEquip(EquipmentSlot.WEAPON, equippedItems, inventory);
        } catch (InvalidEquipmentException err) {
          // TODO
          // Just unequips current weapon if existing. Can maybe handle extraordinary
          // events?
        }

        equippedItems.put(EquipmentSlot.WEAPON, (Weapon) equipment);

        System.out.println("You equip a " + equipment.getName() + " from your inventory.");

        break;

      // All armor slots
      default:
        if (!((EnumSet<ArmorType>) heroType.getValidArmorTypes()).contains(equipment.getEquipmentType())) {
          throw new InvalidEquipmentException(EquipmentErrMessages.WRONG_TYPE);
        }

        try {
          unEquip(equipment.getEquipmentSlot(), equippedItems, inventory);
        } catch (InvalidEquipmentException err) {
          // TODO
          // Just unequips current weapon if existing. Can maybe handle extraordinary
          // events?
        }

        equippedItems.put(equipment.getEquipmentSlot(), (Armor) equipment);
    }
  };

  public void unEquip(EquipmentSlot equipmentSlot) throws InvalidEquipmentException, InventoryException;

  default void unEquip(EquipmentSlot equipmentSlot,
      EnumMap<EquipmentSlot, Equippable> equippedItems,
      List<Item> inventory)
      throws InvalidEquipmentException, InventoryException {

    Equippable unEquipped = equippedItems.get(equipmentSlot);

    if (unEquipped == null)
      throw new InvalidEquipmentException(EquipmentErrMessages.SLOT_EMPTY);

    if (inventory.size() >= 15) {
      throw new InventoryException(InventoryErrMessages.NO_SPACE);
    }

    equippedItems.remove(equipmentSlot);

    if (unEquipped instanceof Weapon) {
      inventory.add((Weapon) unEquipped);

    } else if (unEquipped instanceof Armor) {
      inventory.add((Armor) unEquipped);
    }

    System.out.println(unEquipped.getName() + " was unequipped and added to the inventory.");
  };

  Weapon getEquippedWeapon() throws InvalidEquipmentException;
}