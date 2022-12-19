package main.Game.Swing.Sprites;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background extends JPanel {
  private Image image;
  private int bgX, bgY;

  public Background(String path, int bgX, int bgY) throws IOException {
    this.image = ImageIO.read(new File(path));

    this.bgX = bgX;
    this.bgY = bgY;
  }

  public Image getImage() {
    return image;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(image, bgX, bgY, null);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(image.getWidth(this), image.getHeight(this));
  }

}
