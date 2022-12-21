package main.Game.Swing.Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;

import javax.swing.JPanel;

import main.Game.Components.GameCharacters.Interfaces.Combatant;
import main.Game.Swing.Avatar;

public class HealthBars extends JPanel {
  HashSet<Avatar> avatars = new HashSet<>();
  HeroAvatar heroAvatar;

  public HealthBars(HeroAvatar heroAvatar) {
    this.heroAvatar = heroAvatar;
  }

  public void addAvatar(Avatar avatar) {
    avatars.add(avatar);
  }

  public void remove(Avatar avatar) {
    avatars.remove(avatar);
  }

  public int calculateRemainingBar(Avatar avatar, int healthBarWidth) {
    Combatant character = avatar.getCharacter();
    int level = character.getLevel();
    int initialHealth = 10 + level * 2 - 2;
    double healthRemaining = character.getHealth();

    double remainingPixels = healthBarWidth / (initialHealth / healthRemaining);

    return (int) remainingPixels;

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    avatars.forEach(avatar -> {
      int x = avatar.getSpriteX();
      int y = avatar.getSpriteY();

      if (avatar instanceof VillainAvatar) {
        x -= heroAvatar.getCamX();
        y -= heroAvatar.getCamY();
      }

      // Outline
      g.setColor(Color.red);
      g.fillRect(x, y - 25, 60, 10);

      // HP Left
      int remaining = calculateRemainingBar(avatar, 60);
      g.setColor(Color.green);
      g.fillRect(x, y - 25, remaining, 10);

    });

  }

}
