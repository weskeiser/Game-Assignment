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
  // HashSet<Villain> villains = gameController.newGang(
  // new HashSet<String>(List.of("Villian 1", "Villian 2", "Villian 3")),
  // VillainType.HULK, WeaponItem.GREATAXE);

  GameController gameController = new GameController();

  // CombatAction combatTasks = new CombatAction.CombatTasksBuilder().build();
  // Timer gameTimer = new Timer();

  Hero hero = gameController.newMage("Trollie");

  Villain villian = gameController.newVillain("Villian", VillainType.HULK, WeaponItem.GREATAXE);

  Board board = new Board.BoardBuilder(hero)
      .setBackground("src/lib/img/sprites/Background2.jpg", 1920, 1120)
      .setDimensions(0, 0, 960, 540)
      .setVillain(villian)
      .build();

  //

  public Game() throws IOException {

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

  // public Game() throws IOException {
  // GameController gameController = new GameController();
  // CombatAction combatTasks = new CombatAction.CombatTasksBuilder().build();
  // Timer gameTimer = new Timer();
  // gameTimer.scheduleAtFixedRate(combatTasks, 0, GameTask.GAME_TICK_LENGTH);

  // Hero troll = gameController.newMage("Troll");
  // Hero rogie = gameController.newRogue("Rogie");
  // Hero rangie = gameController.newRanger("Rangie");

  // rangie.levelUp();
  // gameController.equip(0, rangie);

  // Villain villo = new Villain.VillainBuilder("Villo", VillainType.HULK)
  // .provideWeapon(new
  // Weapon.WeaponBuilder(WeaponItem.BLOODY_DAGGER).build()).build();

  // villo.display();

  // combatTasks.newAttack(rogie, villo);
  // // combatTasks.newAttack(troll, rogie);
  // }

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
