package Game.Exceptions;

public class InventoryException extends Throwable {
  public enum InventoryErrMessages {
    NO_SPACE("Could not add item. Inventory is full."),
    EMPTY("Could not drop item. Item not found."),
    NOT_FOUND("Item not found in inventory."),
    ;

    String errorMessage;

    public String msg() {
      return errorMessage;
    }

    InventoryErrMessages(String errorMessage) {
      this.errorMessage = errorMessage;
    }
  }

  public InventoryException(InventoryErrMessages err) {
    super(err.msg());
  }
}
