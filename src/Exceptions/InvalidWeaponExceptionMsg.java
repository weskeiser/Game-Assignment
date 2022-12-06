package Exceptions;

public enum InvalidWeaponExceptionMsg {
  LEVEL_REQUIREMENT("Level is insufficient for wielding this weapon."),
  WRONG_TYPE("This type of weapon is not available for your class."),
  ;

  String errorMessage;

  public String msg() {
    return errorMessage;
  }

  InvalidWeaponExceptionMsg(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
