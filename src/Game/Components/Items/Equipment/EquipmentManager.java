package Game.Components.Items.Equipment;

import java.util.EnumMap;
import java.util.List;

import Game.Components.Exceptions.InvalidEquipmentException;
import Game.Components.Exceptions.InvalidEquipmentException.EquipmentErrMessages;
import Game.Components.Exceptions.InventoryException;
import Game.Components.Exceptions.InventoryException.InventoryErrMessages;
import Game.Components.GameCharacters.Hero.HeroType;
import Game.Components.Items.Item;
import Game.Components.Items.Equipment.Armor.Armor;
import Game.Components.Items.Equipment.Weapon.Weapon;

public interface EquipmentManager {
  EnumMap<EquipmentSlot, Equippable> getEquippedItems();

  Weapon getEquippedWeapon() throws InvalidEquipmentException;

  void equip(int inventoryIndex) throws InvalidEquipmentException, InventoryException;

  void equip(Equippable equipment) throws InvalidEquipmentException,
      InventoryException;

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

    if (!(heroType.getValidArmorTypes()).contains(equipment.getEquipmentType())) {
      throw new InvalidEquipmentException(EquipmentErrMessages.WRONG_TYPE);
    }

    try {
      unEquip(equipment.getEquipmentSlot());
    } catch (InvalidEquipmentException err) {
      if (err.getMessage() != EquipmentErrMessages.SLOT_EMPTY.msg()) {
        System.out.println(err);
      }
    }

    equippedItems.put(equipment.getEquipmentSlot(), equipment);
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

}