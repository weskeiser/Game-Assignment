package main.Game.Swing.Panels;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.Game.Components.GameCharacters.Villain.Villain;

public class EnemyProfile extends JPanel {
  Villain villain;

  private final int profileX = 0;
  private final int profileY = 40;
  private final int boardWidth;
  private final int statsHeight = 100;

  private final int smallSpacing = 60;
  private final int bigSpacing = 90;

  private final int textPositionY = 100;

  private Rectangle profilePhotoPosition;

  private Image profilePhoto;

  public EnemyProfile(Villain villain, int boardWidth) {
    this.villain = villain;

    this.boardWidth = boardWidth;
    this.profilePhotoPosition = new Rectangle(boardWidth - 100, textPositionY - 40, 70, 70);

    String villainType = "skeleton";

    profilePhoto = new ImageIcon(
        "src/lib/img/characters/villain/" + villainType + "-idle-right.gif")
        .getImage();

  }

  public Rectangle getProfilePhotoPosition() {
    return profilePhotoPosition;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int xSpacing = profileX + 35;

    int health = (int) (villain.getHealth());
    Color healthColor = health > health / 2 ? Color.green : Color.orange;

    g.setColor(Color.black);
    g.fillRect(profileX, profileY, boardWidth, statsHeight);

    //
    // ~ Name and photo
    g.setColor(Color.WHITE);

    g.drawString(villain.getName(), xSpacing, textPositionY);

    //
    // ~ Lvl
    g.setColor(Color.WHITE);

    xSpacing += smallSpacing;
    xSpacing += bigSpacing;

    g.drawString("Lvl:", xSpacing, textPositionY);

    xSpacing += smallSpacing;

    g.drawString(Integer.toString(villain.getLevel()), xSpacing, textPositionY);

    //
    // ~ HP
    g.setColor(healthColor);
    g.drawString("HP:", profilePhotoPosition.x - bigSpacing - smallSpacing, textPositionY);

    xSpacing += bigSpacing;

    g.drawString(Integer.toString(health), profilePhotoPosition.x - bigSpacing, textPositionY);

    g.drawImage(profilePhoto, profilePhotoPosition.x, profilePhotoPosition.y, this);
    g.drawRect(profilePhotoPosition.x, profilePhotoPosition.y, profilePhotoPosition.width, profilePhotoPosition.height);
  }

}
