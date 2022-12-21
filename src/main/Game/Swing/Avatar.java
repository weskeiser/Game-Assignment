package main.Game.Swing;

import java.awt.Image;

import main.Game.Components.GameCharacters.Interfaces.Combatant;

public interface Avatar {

  Combatant getCharacter();

  int getSpriteX();

  int getSpriteY();

  void loadWalkingImage(Direction movementDirection);

  Image getImage();

  void move();

}
