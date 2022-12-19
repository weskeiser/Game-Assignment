package main.Game.Swing.Sprites;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import main.Game.Components.GameCharacters.Hero.Hero;

public class Player {
  private Hero hero;
  private double currentHealth;

  private int pdX, pdY;
  public boolean facedRight;
  protected Image image;
  private String imageUrl = "src/lib/img/sprites/Bunny.png";
  private String imageFlippedUrl = "src/lib/img/sprites/BunnyFlip.png";

  private int pX, pY, pWidth, pHeight;
  private int worldW, worldH;

  public Player(Hero hero, int boardWidth, int boardHeight, int worldW, int worldH) {
    this.pX = boardWidth / 2;
    this.pY = boardHeight / 2;
    this.hero = hero;
    this.worldW = worldW;
    this.worldH = worldH;

    this.hero = hero;

    loadImage(imageUrl);

    currentHealth = hero.getHealth();
  }

  public int getHeight() {
    return pHeight;
  }

  public int getWidth() {
    return pWidth;
  }

  protected boolean visible = true;

  private void setHeroDirection() {

    if (facedRight) {
      loadImage(imageFlippedUrl);
    } else {
      loadImage(imageUrl);
    }
  }

  protected void loadImage(String imageUrl) {

    ImageIcon imageIcon = new ImageIcon(imageUrl);
    image = imageIcon.getImage();

    pWidth = image.getWidth(null);
    pHeight = image.getHeight(null);
  }

  public Image getImage() {
    return image;
  }

  public int getX() {
    return pX;
  }

  public int getY() {
    return pY;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  public Rectangle getBounds() {
    return new Rectangle(pX, pY, pWidth, pHeight);
  }

  public void flipImageHorizontally() {
    var tx = AffineTransform.getScaleInstance(-1, 1);
    tx.translate(-image.getWidth(null), 0);

    var op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    image = op.filter((BufferedImage) image, null);
  }

  public int getXDistanceFromTarget(int targetX) {
    return targetX - pX;
  }

  public int getYDistanceFromTarget(int targetY) {
    return targetY - pY;
  }

  public void move() {
    pX += pdX;
    pY += pdY;

    if (pX <= 0) {
      pX = 0;
    }

    if (pY <= 0) {
      pY = 0;
    }

    if (pX >= worldW - pWidth) {
      pX = worldW - pWidth;
    }

    if (pY >= worldH - pHeight) {
      pY = worldH - pHeight;
    }
  }

  public void keyPressed(KeyEvent e) {

    int key = e.getKeyCode();

    switch (key) {
      case KeyEvent.VK_H:
        pdX = -2;
        facedRight = false;
        break;
      case KeyEvent.VK_L:
        pdX = 2;
        facedRight = true;
        break;
      case KeyEvent.VK_K:
        pdY = -2;
        break;
      case KeyEvent.VK_J:
        pdY = 2;
        break;
    }

    setHeroDirection();
  }

  public void keyReleased(KeyEvent e) {

    int key = e.getKeyCode();

    switch (key) {
      case KeyEvent.VK_H:
        pdX = 0;
        break;
      case KeyEvent.VK_L:
        pdX = 0;
        break;
      case KeyEvent.VK_K:
        pdY = 0;
        break;
      case KeyEvent.VK_J:
        pdY = 0;
        break;
    }
  }

}
