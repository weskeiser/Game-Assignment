package main.Game.Components.Exceptions;

public class CombatException extends Throwable {
  public enum Messages {
    NOT_YOUR_KILL("Not your kill.");
    ;

    String errorMessage;

    public String msg() {
      return errorMessage;
    }

    Messages(String errorMessage) {
      this.errorMessage = errorMessage;
    }
  }

  public CombatException(Messages err) {
    super(err.msg());
  }
}
