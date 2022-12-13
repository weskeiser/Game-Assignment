import java.util.*;

import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Interfaces.GameCharacter;
import Game.GameCharacters.Remains.Remains;
import GameController.GameController;
import TickActions.CombatTasks;

public class App {
    public static void main(String[] args) throws Exception {
        Map<GameCharacter, Remains> killedCharacters = new HashMap<>();
        GameController gameController = new GameController();

        CombatTasks combatTasks = new CombatTasks.CombatTasksBuilder().build();

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(combatTasks, 0, 250);

        // combatTasks.run();

        //

        Hero troll = gameController.newMage("Troll");
        Hero rogie = gameController.newRogue("Rogie");

        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();
        troll.levelUp();

        try {
            troll.equip(0);
        } catch (Throwable err) {
            System.out.println(err);
        }

        troll.display();

        combatTasks.newAttack(troll, rogie);

        // troll.display();
    }
}
