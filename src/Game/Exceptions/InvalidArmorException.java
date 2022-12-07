package Game.Exceptions;

public class InvalidArmorException extends Throwable {

  public enum Messages {
    LEVEL_REQUIREMENT("Level is insufficient for this piece of armor."),
    WRONG_TYPE("This type of armor is not available for your class."),
    ;

    String errorMessage;

    public String msg() {
      return errorMessage;
    }

    Messages(String errorMessage) {
      this.errorMessage = errorMessage;
    }
  }

  public InvalidArmorException(Messages err) {
    super(err.msg());
  }

}