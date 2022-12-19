package main.Game.Swing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import main.Game.Swing.Sprites.Player;

public class Camera extends JPanel implements KeyListener {

  private int camX, camY, camW, camH, worldW, worldH, dx, dy;

  Player player;

  public Camera(Player player, int camX, int camY, int boardW, int boardH, int worldW, int worldH) {
    this.player = player;

    this.camX = camX;
    this.camY = camY;
    this.camW = boardW;
    this.camH = boardH;

    this.worldW = worldW;
    this.worldH = worldH;

    addKeyListener(this);
    setFocusable(true);
  }

  public void move() {

    if (camX <= 0) {
      camX = 0;
    }
    if (camY <= 0) {
      camY = 0;
    }

    if (camX >= worldW - camW && player.getX() >= worldW - camW / 2) {
      camX = worldW - camW;
    }

    if (camY >= worldH - camH && player.getY() >= worldH - camH / 2) {
      camY = worldH - camH;
    }

    if (player.getX() - camX == camW / 2) {
      camX += dx;
    }

    if (player.getY() - camY == camH / 2) {
      camY += dy;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // names might be incorrect
    int widthBorder = worldW - (camW / 2);
    int heightBorder = worldH - (camH / 2);

    int key = e.getKeyCode();

    if (camX >= 0 && camX <= widthBorder || camY >= 0 && camY <= heightBorder) {
      switch (key) {
        case KeyEvent.VK_H:
          dx = -2;
          break;
        case KeyEvent.VK_L:
          dx = 2;
          break;
        case KeyEvent.VK_K:
          dy = -2;
          break;
        case KeyEvent.VK_J:
          dy = 2;
          break;
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int widthBorder = worldW - (camW / 2);
    int heightBorder = worldH - (camH / 2);

    int key = e.getKeyCode();

    if (camX >= 0 && camX <= widthBorder || camY >= 0 && camY <= heightBorder) {

      switch (key) {
        case KeyEvent.VK_H:
          dx = 0;
          break;
        case KeyEvent.VK_L:
          dx = 0;
          break;
        case KeyEvent.VK_K:
          dy = 0;
          break;
        case KeyEvent.VK_J:
          dy = 0;
          break;
      }
    }
  }

  public int getX() {
    return camX;
  }

  public int getY() {
    return camY;
  }

}
