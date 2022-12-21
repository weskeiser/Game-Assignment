package main.Game.Swing.Panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.Game.Components.GameCharacters.Hero.Hero;

public class InventoryPanel extends JPanel {
  Hero hero;

  private final int inventoryX;
  private final int inventoryY;
  private final int boardWidth;
  private final int boardHeight;

  private final int slotWidth = 60;
  private final int smallSpacing = 28;
  private final int bigSpacing = 90;

  public InventoryPanel(Hero hero, int boardWidth, int boardHeight) {
    this.hero = hero;

    inventoryX = boardWidth / 2 + 80;
    inventoryY = 150;

    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.lightGray);
    g.drawRect(inventoryX, inventoryY, boardWidth / 2 - 100, boardHeight - 200);

    var itemX = inventoryX + smallSpacing;
    var itemY = inventoryY + smallSpacing;

    g.fillRect(itemX, itemY, slotWidth, slotWidth);

    itemX += slotWidth + smallSpacing;

    g.fillRect(itemX, itemY, slotWidth, slotWidth);

    itemX += slotWidth + smallSpacing;

    g.fillRect(itemX, itemY, slotWidth, slotWidth);

    itemX += slotWidth + smallSpacing;

    g.fillRect(itemX, itemY, slotWidth, slotWidth);

  }

}
