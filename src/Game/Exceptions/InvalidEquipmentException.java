package Game.Exceptions;

public class InvalidEquipmentException extends Throwable {
  public enum Messages {
    LEVEL_REQUIREMENT("Your hero is too noob for this equipment."),
    SLOT_EMPTY("Equipment slot is already empty."),
    ;

    String errorMessage;

    public String msg() {
      return errorMessage;
    }

    Messages(String errorMessage) {
      this.errorMessage = errorMessage;
    }
  }

  public InvalidEquipmentException(Messages err) {
    super(err.msg());
  }

}
