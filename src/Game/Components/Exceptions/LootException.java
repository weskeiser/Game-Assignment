package Game.Components.Exceptions;

public class LootException extends Throwable {
  public enum Messages {
    NOT_FOUND("Item not found in loot pile."),
    NOT_YOURS("This loot pile does not belong to you."),
    FULL_INVENTORY("Your inventory is full.");
    ;

    String errorMessage;

    public String msg() {
      return errorMessage;
    }

    Messages(String errorMessage) {
      this.errorMessage = errorMessage;
    }
  }

  public LootException(Messages err) {
    super(err.msg());
  }
}
