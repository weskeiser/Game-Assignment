package main.Game.Components.Items.Equipment;

import java.util.*;

import main.Game.Components.Exceptions.InvalidEquipmentException;
import main.Game.Components.Exceptions.InvalidEquipmentException.EquipmentErrMessages;
import main.Game.Components.Exceptions.InventoryException;
import main.Game.Components.Exceptions.InventoryException.InventoryErrMessages;
import main.Game.Components.GameCharacters.Hero.HeroType;
import main.Game.Components.GameCharacters.Interfaces.InventoryManager;
import main.Game.Components.Items.GameItem;
import main.Game.Components.Items.Equipment.Armor.Armor;
import main.Game.Components.Items.Equipment.Weapon.Weapon;

public interface EquipmentManager {
  EnumMap<EquipmentSlot, Equippable> getEquippedItems();

  Optional<Weapon> getEquippedWeapon();

  void equip(int inventoryIndex) throws InvalidEquipmentException, InventoryException;

  void equip(Equippable equipment) throws InvalidEquipmentException,
      InventoryException;

  default void equip(Equippable equipment,
      List<GameItem> inventory,
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

    var validEquipmentTypes = equipment.getEquipmentSlot() == EquipmentSlot.WEAPON
        ? heroType.getValidWeaponTypes()
        : heroType.getValidArmorTypes();

    if (!validEquipmentTypes.contains(equipment.getEquipmentType())) {
      throw new InvalidEquipmentException(EquipmentErrMessages.WRONG_TYPE);
    }

    try {
      unEquip(equipment.getEquipmentSlot());
    } catch (InvalidEquipmentException err) {
      if (err.getMessage() != EquipmentErrMessages.SLOT_EMPTY.msg()) {
        System.out.println(err);
      }
    } finally {
      equippedItems.put(equipment.getEquipmentSlot(), equipment);
    }

  };

  public void unEquip(EquipmentSlot equipmentSlot) throws InvalidEquipmentException, InventoryException;

  default void unEquip(EquipmentSlot equipmentSlot,
      EnumMap<EquipmentSlot, Equippable> equippedItems,
      List<GameItem> inventory)
      throws InvalidEquipmentException, InventoryException {

    var equippedItem = equippedItems.get(equipmentSlot);

    if (equippedItem == null)
      throw new InvalidEquipmentException(EquipmentErrMessages.SLOT_EMPTY);

    if (inventory.size() >= InventoryManager.MAX_INVENTORY_SIZE) {
      throw new InventoryException(InventoryErrMessages.NO_SPACE);
    }

    equippedItems.remove(equipmentSlot);

    if (equippedItem instanceof Armor) {
      inventory.add((Armor) equippedItem);
    } else if (equippedItem instanceof Weapon) {
      inventory.add((Weapon) equippedItem);
    }
  };

}