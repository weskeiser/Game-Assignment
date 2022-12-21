package main.Game.Swing.Panels;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.Game.Components.GameCharacters.Interfaces.Combatant;
import main.Game.Swing.Avatar;
import main.Game.Swing.Direction;

public class VillainAvatar extends JPanel implements Avatar {

  private Image walkLeftImg;
  private Image walkRightImg;
  private Image idleLeftImg;
  private Image idleRightImg;

  private Image image;

  private int spriteX, spriteY;

  private Combatant character;

  public Combatant getCharacter() {
    return character;
  }

  Direction movementDirection = Direction.NONE;

  Timer roamTimer = new Timer();
  TimerTask roamTask;

  public void changeDirection() {
    var roll4 = new Random().nextInt(4) + 1;

    switch (roll4) {
      case 1:
        movementDirection = Direction.LEFT;
        image = walkLeftImg;
        break;
      case 2:
        movementDirection = Direction.RIGHT;
        image = walkRightImg;
        break;
      case 3:
        movementDirection = Direction.UP;
        image = idleRightImg;
        break;
      case 4:
        movementDirection = Direction.DOWN;
        image = idleRightImg;
        break;
    }
  }

  public VillainAvatar(Combatant character, int boardX, int boardY, int boardW, int boardH) {
    String villainType = "skeleton";

    this.spriteX = 900;
    this.spriteY = 370;

    this.character = character;

    this.roamTask = new TimerTask() {
      @Override
      public void run() {
        changeDirection();
      }
    };

    setFocusable(true);

    walkLeftImg = new ImageIcon(
        "src/lib/img/characters/villain/" + villainType + "-walk-left.gif")
        .getImage();
    walkRightImg = new ImageIcon(
        "src/lib/img/characters/villain/" + villainType + "-walk-right.gif").getImage();
    idleLeftImg = new ImageIcon(
        "src/lib/img/characters/villain/" + villainType + "-idle-left.gif").getImage();
    idleRightImg = new ImageIcon(
        "src/lib/img/characters/villain/" + villainType + "-idle-right.gif").getImage();

    image = idleLeftImg;

    roamTimer.scheduleAtFixedRate(roamTask, 300, 6000);

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
        image = walkLeftImg;

    }
  }

  public Image getImage() {
    return image;
  }

  public Rectangle getRelativeBounds(int heroCamX, int heroCamY) {
    return new Rectangle(spriteX - heroCamX, spriteY - heroCamY, image.getWidth(null) + 50,
        image.getHeight(null) + 50);
  }

  public int getXDistanceFromHero(int heroX, int heroCamX) {
    return heroX - heroCamX - spriteX;
  }

  public int getYDistanceFromHero(int heroY, int heroCamY) {
    return heroY - heroCamY - spriteY;
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

    switch (movementDirection) {

      case NONE:
        return;

      case LEFT:
        if (spriteX > 0) {
          spriteX -= 1;
        } else
          changeDirection();
        break;

      case RIGHT:
        if (spriteX < 1700) {
          spriteX += 1;
        } else
          changeDirection();
        break;

      case DOWN:
        if (spriteY < 1000) {
          spriteY += 1;
        } else
          changeDirection();
        break;

      case UP:
        if (spriteY > 370) {
          spriteY -= 1;
        } else
          changeDirection();
        break;

    }
  }

}
