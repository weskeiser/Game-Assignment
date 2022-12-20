package main.Game.Swing;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.Game.Components.GameCharacters.Hero.Hero;

public class HeroAvatar extends JPanel implements KeyListener {
  private Image jumpImgLeftUrl = new ImageIcon("src/lib/img/sprites/Jump-left.png").getImage();
  private Image jumpImgRightUrl = new ImageIcon("src/lib/img/sprites/Jump-right.png").getImage();
  private Image stillImageLeftUrl = new ImageIcon("src/lib/img/sprites/Wes-still-left.png").getImage();
  private Image stillImageRightUrl = new ImageIcon("src/lib/img/sprites/Wes-still-right.png").getImage();
  private Image image = stillImageLeftUrl;

  private int worldWidth, worldHeight;
  private int camX, camY, camWidth, camHeight;
  private int spriteX, spriteY;

  private Hero hero;
  private double currentHealth;

  Direction movementDirection = Direction.NONE;

  public HeroAvatar(Hero hero, int boardX, int boardY, int boardW, int boardH, int worldWidth, int worldHeight) {
    this.spriteX = boardW / 2;
    this.spriteY = boardH / 2;

    this.camX = 500;
    this.camY = 100;
    this.camWidth = boardW;
    this.camHeight = boardH;

    this.worldWidth = worldWidth;
    this.worldHeight = worldHeight;

    this.hero = hero;

    addKeyListener(this);
    setFocusable(true);
  }

  public int getCamX() {
    return camX;
  }

  public int getCamY() {
    return camY;
  }

  public int getSpriteX() {
    return spriteX;
  }

  public int getSpriteY() {
    return spriteY;
  }

  protected void loadImage(Direction movementDirection) {

    switch (movementDirection) {
      case LEFT:
        image = jumpImgLeftUrl;
        break;
      case RIGHT:
        image = jumpImgRightUrl;
        break;
      default:
        image = jumpImgLeftUrl;

    }
  }

  public Image getImage() {
    return image;
  }

  public Rectangle getBounds() {
    return new Rectangle(spriteX, spriteY, image.getWidth(null), image.getHeight(null));
  }

  public int getXDistanceFromTarget(int targetX) {
    return targetX - spriteX;
  }

  public int getYDistanceFromTarget(int targetY) {
    return targetY - spriteY;
  }

  public void move() {
    if (movementDirection == Direction.NONE)
      return;

    moveCam();
    moveSprite();
  }

  public void moveCam() {
    int halfCamWidth = camWidth / 2;
    int leftCamEdge = 0;
    int bottomCamEdge = worldHeight - camHeight;
    int rightCamEdge = worldWidth - camWidth;

    boolean withinBoundsLeft = spriteX <= worldWidth - halfCamWidth && camX > leftCamEdge && camX < camWidth
        || camX > camWidth && spriteX <= halfCamWidth;

    boolean withinBoundsRight = spriteX >= halfCamWidth && camX < camWidth;

    switch (movementDirection) {
      case LEFT:
        if (withinBoundsLeft)
          camX -= 25;
        break;

      case RIGHT:
        if (withinBoundsRight)
          camX += 25;
        break;

      case DOWN:
        if (camY < bottomCamEdge && camY >= 100 && spriteY >= 270)
          camY += 25;
        break;

      case UP:
        if (camY > 100 && camY <= bottomCamEdge + 10 && spriteY <= 270)
          camY -= 25;
        break;
    }

  }

  public void moveSprite() {
    int halfCamWidth = camWidth / 2;

    int rightCamEdge = worldWidth - camWidth;

    int bottomCamEdge = worldHeight - camHeight;

    boolean isLeftOfLeftCamEdge = camX <= 0;

    switch (movementDirection) {

      case LEFT:
        if (isLeftOfLeftCamEdge && spriteX > 26 || camX >= rightCamEdge && spriteX > halfCamWidth)
          spriteX -= 25;
        break;

      case RIGHT:
        if (isLeftOfLeftCamEdge || camX >= rightCamEdge && spriteX < rightCamEdge - 60)
          spriteX += 25;
        break;

      case DOWN:
        if (camY >= bottomCamEdge - 26 && spriteY < bottomCamEdge - 100 || camY <= 100)
          spriteY += 25;
        break;

      case UP:
        if (camY > bottomCamEdge)
          spriteY -= 25;
        break;

    }

  }

  @Override
  public void keyPressed(KeyEvent e) {

    int key = e.getKeyCode();

    switch (key) {

      case KeyEvent.VK_H:
        loadImage(Direction.LEFT);
        movementDirection = Direction.LEFT;
        break;

      case KeyEvent.VK_L:
        loadImage(Direction.RIGHT);
        movementDirection = Direction.RIGHT;
        break;

      case KeyEvent.VK_K:
        movementDirection = Direction.UP;
        break;

      case KeyEvent.VK_J:
        movementDirection = Direction.DOWN;
        break;
    }

  }

  public void keyReleased(KeyEvent e) {

    movementDirection = Direction.NONE;

    int key = e.getKeyCode();

    switch (key) {
      case KeyEvent.VK_H:
        image = stillImageLeftUrl;
        break;
      case KeyEvent.VK_L:
        image = stillImageRightUrl;
        break;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

}
