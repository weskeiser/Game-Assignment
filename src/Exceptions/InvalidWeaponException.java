package Exceptions;

public class InvalidWeaponException extends Throwable {
  public enum Messages {
    LEVEL_REQUIREMENT("Level is insufficient for wielding this weapon."),
    WRONG_TYPE("This type of weapon is not available for your class."),
    NO_WEAPON("No weapon in weapon slot."),
    ;

    String errorMessage;

    public String msg() {
      return errorMessage;
    }

    Messages(String errorMessage) {
      this.errorMessage = errorMessage;
    }
  }

  public InvalidWeaponException(Messages err) {
    super(err.msg());
  }
}
