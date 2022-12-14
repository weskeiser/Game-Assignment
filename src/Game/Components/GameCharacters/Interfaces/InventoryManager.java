package Game.Components.GameCharacters.Interfaces;

import java.util.List;

import Game.Components.Exceptions.InventoryException;
import Game.Components.Exceptions.InventoryException.InventoryErrMessages;
import Game.Components.Items.Item;
import Game.Components.Items.Equipment.Armor.Armor;
import Game.Components.Items.Equipment.Weapon.Weapon;

public interface InventoryManager {
  static int MAX_INVENTORY_SIZE = 15;

  void addToInventory(Item item) throws InventoryException;

  default void addToInventory(List<Item> inventory, Item item) throws InventoryException {
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

  Item findInventoryItem(int index) throws InventoryException;

  default boolean removeFromInventory(List<Item> inventory, Item item) throws InventoryException {
    if (!inventory.remove(item))
      throw new InventoryException(InventoryErrMessages.NOT_FOUND);

    return true;
  };

  int getFreeInventorySlots();

}
