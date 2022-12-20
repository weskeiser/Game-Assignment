package main.Game.Swing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.Game.Components.GameCharacters.Hero.Hero;

public class StatsPanel extends JPanel {
  Hero hero;

  private final int statsX = 0;
  private final int statsY = 500;
  private final int statsWidth;
  private final int statsHeight = 100;

  private final int smallSpacing = 60;
  private final int bigSpacing = 90;

  private final int textPositionY = 500 + 55;

  public StatsPanel(Hero hero, int boardWidth) {
    this.hero = hero;

    this.statsWidth = boardWidth;

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.BLUE);
    g.fillRect(statsX, statsY, statsWidth, statsHeight);
    g.setColor(Color.WHITE);

    int xSpacing = statsX + 35;

    // HP
    g.drawString("HP:", xSpacing, textPositionY);
    xSpacing += smallSpacing;
    g.drawString(Integer.toString((int) (hero.getHealth())), xSpacing, textPositionY);

    // Lvl
    xSpacing += bigSpacing;
    g.drawString("Lvl:", xSpacing, textPositionY);
    xSpacing += smallSpacing;
    g.drawString(Integer.toString(hero.getLevel()), xSpacing, textPositionY);

    // Name
    xSpacing += bigSpacing;
    g.drawString(hero.getName(), xSpacing, textPositionY);
  }

}
