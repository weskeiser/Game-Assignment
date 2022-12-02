package Inventory;

import java.util.ArrayList;

import Interfaces.IInventoryManager;
import Interfaces.IItem;

public class Inventory implements IInventoryManager {
  ArrayList<IItem> contents = new ArrayList<IItem>(10);

  Inventory() {
  }

  public boolean insert(IItem item) {
    return true;
  };

  public boolean dropItem(IItem item) {
    return true;
  };

  // public Item[] displayContents() {
  // }

  // private boolean getItem(Item item) {
  // return true;
  // };

}
