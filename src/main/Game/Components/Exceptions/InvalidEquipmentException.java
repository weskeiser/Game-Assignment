package main.Game.Components.Exceptions;

public class InvalidEquipmentException extends Throwable {
  public enum EquipmentErrMessages {
    LEVEL_REQUIREMENT("Your hero is too noob to equip this item."),
    SLOT_EMPTY("Equipment slot is already empty."),
    WRONG_TYPE("This type of equipment is not available for your class."),
    NO_WEAPON("No weapon in weapon slot."),
    ;

    String errorMessage;

    public String msg() {
      return errorMessage;
    }

    EquipmentErrMessages(String errorMessage) {
      this.errorMessage = errorMessage;
    }
  }

  public InvalidEquipmentException(EquipmentErrMessages err) {
    super(err.msg());
  }

}
