package Game;

import java.util.*;

import Game.Components.GameCharacters.Hero.Hero;
import Game.Components.GameCharacters.Interfaces.GameCharacter;
import Game.Components.GameCharacters.Remains.Remains;
import Game.Components.Items.Equipment.Armor.Armor;
import Game.Components.Items.Equipment.Armor.ArmorItems.Mail;
import Game.GameActions.CombatTasks;
import Game.GameController.GameController;

public class Game {
  public static void main(String[] args) throws Exception {
    Map<GameCharacter, Remains> killedCharacters = new HashMap<>();
    GameController gameController = new GameController();

    CombatTasks combatTasks = new CombatTasks.CombatTasksBuilder().build();

    Timer timer = new Timer();

    timer.scheduleAtFixedRate(combatTasks, 0, 250);

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

    System.out.println(rangie.getDefensiveAttributes());
    rangie.display();

    combatTasks.newAttack(rangie, rogie);

    // rangie.display();
  }
}
