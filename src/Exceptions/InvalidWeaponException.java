package Exceptions;

public class InvalidWeaponException extends Throwable {
  public InvalidWeaponException(InvalidWeaponExceptionMsg err) {
    super(err.msg());
  }
}
