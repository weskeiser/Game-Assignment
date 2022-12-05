package Exceptions;

import ENUMS.InvalidArmorExceptionMsg;

public class InvalidArmorException extends Throwable {
  public InvalidArmorException(InvalidArmorExceptionMsg err) {
    super(err.msg());
  }
}