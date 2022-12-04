package Interfaces;

import java.util.ArrayList;

public interface InventoryManager {

  public static boolean insert(ArrayList<Item> inventory, Item item) {
    boolean full = inventory.size() >= 15;

    if (full) {
      System.out.println(" \nCould not fit " + item.displayItemName() + "." +
          "\nInventory is full (" + inventory.size() + "/15).");
      return false;
    }

    inventory.add(item);
    System.out.println("\n" + item.displayItemName() + " was added to the inventory");
    return true;
  };

  public static boolean dropItem(ArrayList<Item> inventory, Item item) {
    boolean dropped = inventory.remove(item);

    if (!dropped) {
      System.out.println("Could not find " + item.displayItemName());
      return false;
    }

    System.out.println(item + " was dropped");
    return true;
  };

}
