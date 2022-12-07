package Game.Items.Inventory;

import java.util.List;

import Game.Exceptions.InventoryException;
import Game.Items.Item;
import Game.Items.ItemManager;
import Game.Items.Equipment.Armor.Armor;
import Game.Items.Equipment.Weapon.Weapon;

public interface InventoryManager extends ItemManager {

  default void addToInventory(List<Item> inventory, Item item) throws InventoryException {
    try {
      if (inventory.size() >= 15) {
        throw new InventoryException(InventoryException.Messages.INVENTORY_FULL);
      }

      if (item instanceof Weapon) {
        inventory.add((Weapon) item);
      } else if (item instanceof Armor)
        inventory.add((Armor) item);
      else {
        inventory.add(item);
      }
    } catch (InventoryException err) {
      throw err;
    }
  };

  default void dropItem(List<Item> inventory, Item item) throws InventoryException {
    try {
      if (!inventory.remove(item))
        throw new InventoryException(InventoryException.Messages.INVENTORY_EMPTY);
      System.out.println(item.getName() + " was dropped.");
    } catch (InventoryException err) {
      throw err;
    }
  };

  void showInventory();

}
