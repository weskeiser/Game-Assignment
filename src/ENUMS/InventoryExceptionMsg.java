package ENUMS;

public enum InventoryExceptionMsg {
  INVENTORY_FULL("Could not add item. Inventory is full."),
  INVENTORY_EMPTY("Could not drop item. Item not found."),
  NOT_FOUND("Item not found in inventory."),
  ;

  String errorMessage;

  public String msg() {
    return errorMessage;
  }

  InventoryExceptionMsg(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
