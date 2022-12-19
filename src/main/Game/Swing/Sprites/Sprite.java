package main.Game.Swing.Sprites;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Sprite {

  protected int x, y;
  public int width, height;
  protected boolean visible;
  protected Image image;

  public Sprite(int x, int y) {
    this.x = x;
    this.y = y;
    visible = true;
  }

  protected void loadImage(String imageName) {

    ImageIcon imageIcon = new ImageIcon(imageName);

    image = imageIcon.getImage();
  }

  protected void getImageDimensions() {
    width = image.getWidth(null);
    height = image.getHeight(null);
  }

  public Image getImage() {
    return image;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  public Rectangle getBounds() {
    return new Rectangle(x, y, width, height);
  }

  public void flipImageHorizontally() {
    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
    tx.translate(-image.getWidth(null), 0);
    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    image = op.filter((BufferedImage) image, null);
  }

  public int getXDistanceFromTarget(int targetX) {
    return targetX - x;
  }

  public int getYDistanceFromTarget(int targetY) {
    return targetY - y;
  }

}
