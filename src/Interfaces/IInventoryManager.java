package Interfaces;

public interface IInventoryManager {

  boolean insert(IItem item);

  boolean dropItem(IItem item);

  // Item[] displayContents();

}
