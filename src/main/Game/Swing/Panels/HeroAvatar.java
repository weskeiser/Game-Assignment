package main.Game.Swing.Panels;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.GameCharacters.Interfaces.Combatant;
import main.Game.Swing.Avatar;
import main.Game.Swing.Direction;

public class HeroAvatar extends JPanel implements Avatar, KeyListener, MouseListener {
  private Image walkLeftImg;
  private Image walkRightImg;
  private Image idleLeftImg;
  private Image idleRightImg;

  private Image image;

  private int worldWidth, worldHeight;
  private int camX, camY;
  private int boardWidth, boardHeight;
  private int spriteX, spriteY;

  private Combatant character;

  public Combatant getCharacter() {
    return character;
  }

  Direction movementDirection = Direction.NONE;

  public void mousePressed(MouseEvent e) {

  }

  public void mouseEntered(MouseEvent e) {

  }

  public void mouseReleased(MouseEvent e) {

  }

  public void mouseClicked(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public HeroAvatar(Combatant character, int boardX, int boardY, int boardWidth, int boardHeight, int worldWidth,
      int worldHeight) {
    this.spriteX = boardWidth / 2;
    this.spriteY = boardHeight / 2;

    this.camX = boardWidth / 2;
    this.camY = 100;
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;

    this.worldWidth = worldWidth;
    this.worldHeight = worldHeight;

    this.character = character;

    String characterType = ((Hero) character).getCharacterType().toString();

    walkLeftImg = new ImageIcon(
        "src/lib/img/characters/hero/" + characterType.toLowerCase() + "-walk-left.gif")
        .getImage();
    walkRightImg = new ImageIcon(
        "src/lib/img/characters/hero/" + characterType.toLowerCase() + "-walk-right.gif").getImage();
    idleLeftImg = new ImageIcon(
        "src/lib/img/characters/hero/" + characterType.toLowerCase() + "-idle-left.gif").getImage();
    idleRightImg = new ImageIcon(
        "src/lib/img/characters/hero/" + characterType.toLowerCase() + "-idle-right.gif").getImage();

    image = idleLeftImg;

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

  public void loadWalkingImage(Direction movementDirection) {

    switch (movementDirection) {
      case LEFT:
        image = walkLeftImg;
        break;
      case RIGHT:
        image = walkRightImg;
        break;
      default:
        image = walkRightImg;

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
    int halfBoardWidth = boardWidth / 2;
    int leftCamEdge = 0;
    int bottomCamEdge = worldHeight - boardHeight;

    boolean withinBoundsLeft = camX > leftCamEdge && camX <= boardWidth
        || camX > boardWidth && spriteX <= halfBoardWidth;

    boolean withinBoundsRight = spriteX >= halfBoardWidth && camX <= boardWidth;

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
    int halfBoardWidth = boardWidth / 2;
    int rightCamEdge = worldWidth - boardWidth;
    int bottomCamEdge = worldHeight - boardHeight;

    boolean isLeftOfLeftCamEdge = camX <= 0;

    switch (movementDirection) {

      case NONE:
        return;

      case LEFT:
        if (isLeftOfLeftCamEdge && spriteX > 26 || camX >= rightCamEdge && spriteX > halfBoardWidth)
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

  @Override
  public void keyPressed(KeyEvent e) {

    var key = e.getKeyCode();

    switch (key) {

      case KeyEvent.VK_S:
        loadWalkingImage(Direction.LEFT);
        movementDirection = Direction.LEFT;
        break;

      case KeyEvent.VK_F:
        loadWalkingImage(Direction.RIGHT);
        movementDirection = Direction.RIGHT;
        break;

      case KeyEvent.VK_E:
        movementDirection = Direction.UP;
        break;

      case KeyEvent.VK_D:
        movementDirection = Direction.DOWN;
        break;

      case KeyEvent.VK_R:
        movementDirection = Direction.DOWN;
        break;
    }

  }

  public void keyReleased(KeyEvent e) {

    movementDirection = Direction.NONE;

    var key = e.getKeyCode();

    switch (key) {
      case KeyEvent.VK_S:
        image = idleLeftImg;
        break;
      case KeyEvent.VK_F:
        image = idleRightImg;
        break;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

}
