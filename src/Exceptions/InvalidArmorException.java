package Exceptions;

public class InvalidArmorException extends Throwable {
  public InvalidArmorException(InvalidArmorExceptionMsg err) {
    super(err.msg());
  }
}