package Interfaces;

public interface InventoryManager extends ItemManager {

  void dropItem(Item item);

  void addToInventory(Item item);

}
