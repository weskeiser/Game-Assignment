package main.Game.Swing;

import java.awt.Image;

public interface Avatar {
  int getCamX();

  int getCamY();

  int getSpriteX();

  int getSpriteY();

  void loadImage(Direction movementDirection);

  Image getImage();

  void move();

}
