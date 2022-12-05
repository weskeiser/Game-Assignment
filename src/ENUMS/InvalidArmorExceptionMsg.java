package ENUMS;

public enum InvalidArmorExceptionMsg {
  LEVEL_REQUIREMENT("Level is insufficient for this piece of armor."),
  WRONG_TYPE("This type of armor is not available for your class."),
  ;

  String errorMessage;

  public String msg() {
    return errorMessage;
  }

  InvalidArmorExceptionMsg(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
