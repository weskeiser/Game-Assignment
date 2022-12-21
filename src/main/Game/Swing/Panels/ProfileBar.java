package main.Game.Swing.Panels;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.Game.Components.GameCharacters.Hero.Hero;

public class ProfileBar extends JPanel {
  Hero hero;

  private final int profileX = 0;
  private final int profileY = 500;
  private final int boardWidth;
  private final int statsHeight = 100;

  private final int smallSpacing = 60;
  private final int bigSpacing = 90;

  private final int textPositionY = 500 + 55;

  private Rectangle profilePhotoPosition;

  private Image profilePhoto;

  public ProfileBar(Hero hero, int boardWidth) {
    this.hero = hero;

    this.boardWidth = boardWidth;
    this.profilePhotoPosition = new Rectangle(boardWidth - 100, textPositionY - 40, 70, 70);

    String characterType = hero.getCharacterType().toString();

    profilePhoto = new ImageIcon(
        "src/lib/img/characters/hero/" + characterType.toLowerCase() + "-portrait.gif")
        .getImage();

  }

  public Rectangle getProfilePhotoPosition() {
    return profilePhotoPosition;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int xSpacing = profileX + 35;

    int health = (int) (hero.getHealth());
    Color healthColor = health > health / 2 ? Color.green : Color.orange;

    g.setColor(Color.darkGray);
    g.fillRect(profileX, profileY, boardWidth, statsHeight);

    //
    // ~ Name and photo
    g.setColor(Color.WHITE);

    g.drawString(hero.getName(), xSpacing, textPositionY);

    //
    // ~ Lvl
    g.setColor(Color.WHITE);

    xSpacing += smallSpacing;
    xSpacing += bigSpacing;

    g.drawString("Lvl:", xSpacing, textPositionY);

    xSpacing += smallSpacing;

    g.drawString(Integer.toString(hero.getLevel()), xSpacing, textPositionY);

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
