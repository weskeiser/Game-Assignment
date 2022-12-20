package main.Game.Swing;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.Game.Components.GameCharacters.Interfaces.Combatant;

public class VillainAvatar extends JPanel implements Avatar {
  private Image runImgLeft = new ImageIcon("src/lib/img/sprites/Jump-left.png").getImage();
  private Image runImgRight = new ImageIcon("src/lib/img/sprites/Jump-right.png").getImage();
  private Image stillImgLeft = new ImageIcon("src/lib/img/sprites/Wes-still-left.png").getImage();
  private Image stillImgRight = new ImageIcon("src/lib/img/sprites/Wes-still-right.png").getImage();
  private Image image = stillImgLeft;

  private int worldWidth, worldHeight;
  private int camX, camY, camWidth, camHeight;
  private int spriteX, spriteY;

  private Combatant character;

  Direction movementDirection = Direction.NONE;

  Timer roamTimer = new Timer();
  TimerTask roamTask;

  public void roam() {
    var roll4 = new Random().nextInt(4) + 1;
    System.out.println(roll4);

    switch (roll4) {
      case 1:
        movementDirection = Direction.LEFT;
        break;
      case 2:
        movementDirection = Direction.RIGHT;
        break;
      case 3:
        movementDirection = Direction.UP;
        break;
      case 4:
        movementDirection = Direction.DOWN;
        break;
    }

    // move();
  }

  public VillainAvatar(Combatant character, int boardX, int boardY, int boardW, int boardH, int worldWidth,
      int worldHeight) {
    this.spriteX = boardW / 2;
    this.spriteY = boardH / 2;

    this.camX = 500;
    this.camY = 100;
    this.camWidth = boardW;
    this.camHeight = boardH;

    this.worldWidth = worldWidth;
    this.worldHeight = worldHeight;

    this.character = character;

    this.roamTask = new TimerTask() {
      @Override
      public void run() {
        roam();
      }
    };

    // setFocusable(true);

    roamTimer.scheduleAtFixedRate(roamTask, 300, 600);
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

  public void loadImage(Direction movementDirection) {

    switch (movementDirection) {
      case LEFT:
        image = runImgLeft;
        break;
      case RIGHT:
        image = runImgRight;
        break;
      default:
        image = runImgLeft;

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

    boolean withinBoundsLeft = camX > leftCamEdge && camX <= camWidth
        || camX > camWidth && spriteX <= halfCamWidth;

    boolean withinBoundsRight = spriteX >= halfCamWidth && camX <= camWidth;

    boolean withinBoundsBottom = camY < bottomCamEdge && camY >= 100 && spriteY >= 270;

    boolean withinBoundsTop = camY > 100 && camY <= bottomCamEdge && spriteY <= 270;

    switch (movementDirection) {
      case NONE:
        return;

      case LEFT:
        if (withinBoundsLeft || withinBoundsRight)
          camX -= 2;
        break;

      case RIGHT:
        if (withinBoundsRight)
          camX += 2;
        break;

      case DOWN:
        if (withinBoundsBottom)
          camY += 2;
        break;

      case UP:
        if (withinBoundsTop)
          camY -= 2;
        break;

    }

  }

  public void moveSprite() {
    int halfCamWidth = camWidth / 2;
    int rightCamEdge = worldWidth - camWidth;
    int bottomCamEdge = worldHeight - camHeight;

    boolean isLeftOfLeftCamEdge = camX <= 0;

    switch (movementDirection) {

      case NONE:
        return;

      case LEFT:
        if (isLeftOfLeftCamEdge && spriteX > 26 || camX >= rightCamEdge && spriteX > halfCamWidth)
          spriteX -= 2;
        break;

      case RIGHT:
        if (isLeftOfLeftCamEdge || camX >= rightCamEdge && spriteX < rightCamEdge - 60)
          spriteX += 2;
        break;

      case DOWN:
        if (camY >= bottomCamEdge && spriteY < bottomCamEdge - 150 || camY <= 100)
          spriteY += 2;
        break;

      case UP:
        if (camY >= bottomCamEdge)
          spriteY -= 2;
        break;

    }

  }

}
