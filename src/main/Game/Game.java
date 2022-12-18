package main.Game;

import java.util.Timer;

import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.Items.Equipment.Armor.Armor;
import main.Game.Components.Items.Equipment.Armor.ArmorItems.Mail;
import main.Game.GameAction.GameTask;
import main.Game.GameAction.Combat.CombatAction;
import main.Game.GameController.GameController;

public class Game {
  public static void main(String[] args) throws Exception {
    GameController gameController = new GameController();

    CombatAction combatTasks = new CombatAction.CombatTasksBuilder().build();

    Timer gameTimer = new Timer();

    gameTimer.scheduleAtFixedRate(combatTasks, 0, GameTask.GAME_TICK_LENGTH);

    Hero troll = gameController.newMage("Troll");
    Hero rogie = gameController.newRogue("Rogie");
    Hero rangie = gameController.newRanger("Rangie");

    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();
    rangie.levelUp();

    try {
      rangie.equip(0);
      Armor royalMail = new Armor.ArmorBuilder(Mail.ROYAL_MAIL).build();
      rangie.addToInventory(royalMail);

      rangie.equip(0);

    } catch (Throwable err) {
      System.out.println(err);
    }

    System.out.println(rangie.getMaxHit());

    System.out.println(rangie.getDefensiveAttributes());
    rangie.display();

    combatTasks.newAttack(rangie, rogie);
    combatTasks.newAttack(troll, rogie);

    // rangie.display();
  }
}
