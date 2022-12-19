package main.Game.Components.GameCharacters.Interfaces;

import java.util.List;

import main.Game.Components.Exceptions.InventoryException;
import main.Game.Components.Exceptions.InventoryException.InventoryErrMessages;
import main.Game.Components.Items.GameItem;
import main.Game.Components.Items.Equipment.Armor.Armor;
import main.Game.Components.Items.Equipment.Weapon.Weapon;

public interface InventoryManager {

  static int MAX_INVENTORY_SIZE = 15;

  int getFreeInventorySlots();

  void addToInventory(GameItem item) throws InventoryException;

  GameItem findInventoryItem(int index) throws InventoryException;

  default void addToInventory(List<GameItem> inventory, GameItem item) throws InventoryException {
    if (inventory.size() >= MAX_INVENTORY_SIZE) {
      throw new InventoryException(InventoryErrMessages.NO_SPACE);
    }

    if (item instanceof Weapon) {
      inventory.add((Weapon) item);
    } else if (item instanceof Armor)
      inventory.add((Armor) item);
    else {
      inventory.add(item);
    }
  };

  default boolean removeFromInventory(List<GameItem> inventory, GameItem item) throws InventoryException {

    if (!inventory.remove(item))
      throw new InventoryException(InventoryErrMessages.NOT_FOUND);

    return true;
  };

}
