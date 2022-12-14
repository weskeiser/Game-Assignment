import java.util.*;

import Components.GameCharacters.Hero.Hero;
import Components.GameCharacters.Interfaces.GameCharacter;
import Components.GameCharacters.Remains.Remains;
import GameController.GameController;
import TickActions.CombatTasks;

public class App {
    public static void main(String[] args) throws Exception {
        Map<GameCharacter, Remains> killedCharacters = new HashMap<>();
        GameController gameController = new GameController();

        CombatTasks combatTasks = new CombatTasks.CombatTasksBuilder().build();

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(combatTasks, 0, GAME_TICK_LENGTH);

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
            troll.equip(0);
        } catch (Throwable err) {
            System.out.println(err);
        }

        combatTasks.newAttack(troll, rogie);

        // rangie.display();
    }
}
