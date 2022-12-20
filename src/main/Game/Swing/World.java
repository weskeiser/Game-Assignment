package main.Game.Swing;

import java.awt.*;
import java.io.IOException;

import javax.swing.JPanel;

import main.Game.Swing.Sprites.Background;

public class World extends JPanel {
  private int worldX, worldY, worldW, worldH;

  private Background background;
  // private Camera camera;
  private HeroAvatar player;

  public World(HeroAvatar player, int worldX, int worldY, int worldW, int worldH, String backgroundImgPath)
      throws IOException {
    // this.camera = cam;
    this.player = player;

    this.worldX = worldX;
    this.worldY = worldY;
    this.worldW = worldW;
    this.worldH = worldH;

    this.background = new Background(backgroundImgPath, worldX, worldY);
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

    g.drawImage(background.getImage(), worldX - player.getCamX(), worldY - player.getCamY(), null);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(worldW, worldH);
  }

}
