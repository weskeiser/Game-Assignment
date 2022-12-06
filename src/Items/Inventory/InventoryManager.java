package Items.Inventory;

import Items.Item;
import Items.ItemManager;

public interface InventoryManager extends ItemManager {

  void dropItem(Item item);

  void addToInventory(Item item);

}
