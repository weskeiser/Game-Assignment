package Exceptions;

public class InventoryException extends Throwable {
  public enum Messages {
    INVENTORY_FULL("Could not add item. Inventory is full."),
    INVENTORY_EMPTY("Could not drop item. Item not found."),
    NOT_FOUND("Item not found in inventory."),
    ;

    String errorMessage;

    public String msg() {
      return errorMessage;
    }

    Messages(String errorMessage) {
      this.errorMessage = errorMessage;
    }
  }

  public InventoryException(Messages err) {
    super(err.msg());
  }
}
