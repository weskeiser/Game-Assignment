package Game.Items;

import java.util.List;

import Game.Exceptions.InventoryException;
import Game.Exceptions.InventoryException.InventoryErrMessages;
import Game.Items.Equipment.Armor.Armor;
import Game.Items.Equipment.Weapon.Weapon;

public interface InventoryManager {

  default void addToInventory(List<Item> inventory, Item item) throws InventoryException {
    if (inventory.size() >= 15) {
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

  public Item getFromInventory(int index) throws InventoryException;

  default Item getFromInventory(List<Item> inventory, int index) throws InventoryException {
    Item retrievedItem = inventory.get(index);

    if (retrievedItem == null)
      throw new InventoryException(InventoryErrMessages.NOT_FOUND);

    return retrievedItem;

  }

  default void dropItem(List<Item> inventory, Item item) throws InventoryException {
    if (!inventory.remove(item))
      throw new InventoryException(InventoryErrMessages.NOT_FOUND);

    System.out.println(item.getName() + " was dropped.");
  };

  void showInventory();

}
