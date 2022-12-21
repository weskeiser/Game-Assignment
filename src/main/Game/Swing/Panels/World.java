package main.Game.Swing.Panels;

import java.awt.*;
import java.io.IOException;

import javax.swing.JPanel;

public class World extends JPanel {
  private int worldX, worldY, worldW, worldH;

  private Background background;
  private HeroAvatar avatar;

  public World(HeroAvatar avatar, int worldX, int worldY, int worldW, int worldH, String backgroundImgPath)
      throws IOException {
    this.avatar = avatar;

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

    g.drawImage(background.getImage(), worldX - avatar.getCamX(), worldY - avatar.getCamY(), null);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(worldW, worldH);
  }

}
