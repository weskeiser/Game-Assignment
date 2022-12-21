package main.Game.Swing.Panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.Game.Components.GameCharacters.Hero.Hero;

public class EquipmentPanel extends JPanel {
  Hero hero;

  private final int equipmentX;
  private final int equipmentY;
  private final int boardWidth;
  private final int boardHeight;

  private final int smallSpacing = 30;
  private final int bigSpacing = 60;

  String weapon = new String();

  String head = new String();
  String torso = new String();
  String legs = new String();

  public EquipmentPanel(Hero hero, int boardWidth, int boardHeight) {
    this.hero = hero;

    equipmentX = 20;
    equipmentY = 150;

    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.lightGray);
    g.drawRect(equipmentX, equipmentY, boardWidth / 2 - 100, boardHeight - 200);

    var slotX = equipmentX + smallSpacing;
    var slotY = equipmentY + bigSpacing + smallSpacing;

    hero.getEquippedItems().forEach((k, v) -> {
      switch (k) {
        case WEAPON:
          weapon = v.getName();
        case HEAD:
          head = v.getName();
        case TORSO:
          torso = v.getName();
        case LEGS:
          legs = v.getName();
      }
    });

    g.setColor(Color.WHITE);

    g.drawString("WEAPON:  " + weapon, slotX, slotY);
    slotY += bigSpacing;
    g.drawString("HEAD:  ", slotX, slotY);
    slotY += bigSpacing;
    g.drawString("TORSO:  ", slotX, slotY);
    slotY += bigSpacing;
    g.drawString("LEGS:  ", slotX, slotY);

  }

}
