package Game;

import java.util.Timer;

import Game.Components.GameCharacters.Hero.Hero;
import Game.Components.Items.Equipment.Armor.Armor;
import Game.Components.Items.Equipment.Armor.ArmorItems.Mail;
import Game.GameAction.GameTask;
import Game.GameAction.Combat.CombatAction;
import Game.GameController.GameController;

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
