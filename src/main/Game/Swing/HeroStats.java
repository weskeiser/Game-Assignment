package main.Game.Swing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.Game.Components.GameCharacters.Hero.Hero;

public class HeroStats extends JPanel {
  Hero hero;

  private int statsX = 0;
  private int statsY = 430;
  private int statsWidth = 960;
  private int statsHeight = 100;

  public HeroStats(Hero hero) {
    this.hero = hero;

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Horisontal container at bottom
    g.setColor(Color.BLACK);
    g.fillRect(statsX, statsY, statsWidth, statsHeight);
    // panelText.drawStandard("HP: " + _player._currentHealth, 9, _cam.getCamH() -
    // 19);

  }

}
