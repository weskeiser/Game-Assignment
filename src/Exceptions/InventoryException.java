package Exceptions;

import ENUMS.InventoryExceptionMsg;

public class InventoryException extends Throwable {
  public InventoryException(InventoryExceptionMsg err) {
    super(err.msg());
  }
}
