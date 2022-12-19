package main.Game.Swing;

import java.awt.*;
import java.io.IOException;

import javax.swing.JPanel;

import main.Game.Swing.Sprites.Background;

public class World extends JPanel {
  private int worldX, worldY, worldW, worldH;

  private Background background;
  private Camera camera;

  public World(Camera cam, int worldX, int worldY, int worldW, int worldH) throws IOException {
    this.camera = cam;

    this.worldX = worldX;
    this.worldY = worldY;
    this.worldW = worldW;
    this.worldH = worldH;

    this.background = new Background("src/lib/img/sprites/Background.jpeg", worldX, worldY);
  }

  public Image getImage() {
    return background.getImage();
  }

  public int getX() {
    return worldX;
  }

  public int getY() {
    return worldY;
  }

  public int getW() {
    return worldW;
  }

  public int getH() {
    return worldH;
  }

  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);

    g.drawImage(background.getImage(), worldX - camera.getX(), worldY - camera.getY(), null);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(worldW, worldH);
  }

}
