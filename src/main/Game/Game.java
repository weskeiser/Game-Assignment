package main.Game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.GameCharacters.Hero.HeroType;
import main.Game.GameController.GameController;
import main.Game.Swing.Board;

public class Game extends JFrame {
  public static final int playerWidth = 28;
  public static final int playerHeight = 26;
  GameController gameController = new GameController();
  Hero hero = new Hero.HeroBuilder("Trollie", HeroType.MAGE).build();

  public Game() throws IOException {

    Board board = new Board(0, 0, 500, 500, hero);
    add(board);
    setSize(board.getBoardWidth(), board.getBoardHeight() + playerHeight);
    setResizable(false);
    setTitle("Test Game Title");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2 - 100);
  }

  // public static void main(String[] args) throws Exception {
  // GameController gameController = new GameController();
  // CombatAction combatTasks = new CombatAction.CombatTasksBuilder().build();
  // Timer gameTimer = new Timer();
  // gameTimer.scheduleAtFixedRate(combatTasks, 0, GameTask.GAME_TICK_LENGTH);

  // Hero troll = gameController.newMage("Troll");
  // Hero rogie = gameController.newRogue("Rogie");
  // Hero rangie = gameController.newRanger("Rangie");

  // rangie.levelUp();
  // gameController.equip(0, rangie);
  // // combatTasks.newAttack(rangie, rogie);
  // // combatTasks.newAttack(troll, rogie);
  // }
}
