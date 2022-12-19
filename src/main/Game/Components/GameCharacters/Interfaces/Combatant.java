package main.Game.Components.GameCharacters.Interfaces;

import java.util.Optional;

public interface Combatant {

  Optional<Defender> getCurrentlyAttacking();

  void setCurrentlyAttacking(Defender currentlyAttacking);

}
