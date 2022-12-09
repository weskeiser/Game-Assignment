package Game.Items;

import java.util.List;

import Game.Exceptions.InventoryException;
import Game.Exceptions.InventoryException.Messages;
import Game.Items.Equipment.Armor.Armor;
import Game.Items.Equipment.Weapon.Weapon;

public interface InventoryManager {

  default void addToInventory(List<Item> inventory, Item item) throws InventoryException {
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
  };

  default Item getFromInventory(List<Item> inventory, int index) throws InventoryException {
    Item retrievedItem = inventory.get(index);

    if (retrievedItem == null)
      throw new InventoryException(Messages.NOT_FOUND);

    return retrievedItem;

  }

  default void dropItem(List<Item> inventory, Item item) throws InventoryException {
    if (!inventory.remove(item))
      throw new InventoryException(InventoryException.Messages.NOT_FOUND);

    System.out.println(item.getName() + " was dropped.");
  };

  void showInventory();

}
