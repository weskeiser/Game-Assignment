package main.Game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.swing.JFrame;

import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.GameCharacters.Hero.HeroType;
import main.Game.Components.GameCharacters.Villain.Villain;
import main.Game.Components.GameCharacters.Villain.VillainType;
import main.Game.Components.Items.Equipment.Weapon.WeaponItem;
import main.Game.GameController.GameController;
import main.Game.Swing.Board;

public class Game extends JFrame {

  GameController gameController = new GameController();

  Hero hero = new Hero.HeroBuilder("Trollie", HeroType.MAGE).build();
  HashSet<Villain> villains = gameController.newGang(
      new HashSet<String>(List.of("Villian 1", "Villian 2", "Villian 3")), VillainType.HULK, WeaponItem.GREATAXE);

  //

  public Game() throws IOException {

    Board board = new Board.BoardBuilder(hero).setDimensions(0, 0, 960, 540)
        .setBackground("src/lib/img/sprites/Background2.jpg", 0, 0).setVillains(villains).build();

    add(board);
    setSize(board.getBoardWidth(), board.getBoardHeight() + Board.playerHeight);
    setResizable(false);
    setTitle("Test Game Title");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height -
        this.getHeight()) / 2 - 100);
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
