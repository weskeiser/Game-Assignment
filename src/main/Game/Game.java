package main.Game;

import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.GameCharacters.Villain.Villain;
import main.Game.Components.GameCharacters.Villain.VillainType;
import main.Game.Components.Items.Equipment.Weapon.WeaponItem;
import main.Game.GameController.GameController;
import main.Game.Swing.Panels.Board;

public class Game extends JFrame {

  GameController gameController = new GameController();

  Hero hero = gameController.newMage("Trollie");
  Villain villain = gameController.newVillain("Skelespear", VillainType.SKELESPEAR, WeaponItem.GREATAXE);

  Board board = new Board.BoardBuilder(hero)
      .setBackground("src/lib/img/sprites/Background2.jpg", 1920, 1120)
      .setDimensions(0, 0, 960, 540)
      .setVillain(villain)
      .build();

  //

  public Game() throws IOException {
    try {
      hero.equip(0);
    } catch (Throwable err) {
      System.out.println(err);
    }

    applyBoardSettings();

    add(board);
    setTitle("Test Game Title");
    setSize(board.getBoardWidth(), board.getBoardHeight() + 100);

  }

  public void applyBoardSettings() {
    var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((screenSize.width - this.getWidth()) / 2 - 300, (screenSize.height - this.getHeight()) / 2 - 100);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
}
