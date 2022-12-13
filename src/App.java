import java.util.*;

import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Interfaces.GameCharacter;
import Game.GameCharacters.Remains.Remains;
import GameController.GameController;
import TickActions.TickActions;

public class App {
    public static void main(String[] args) throws Exception {
        Map<GameCharacter, Remains> killedCharacters = new HashMap<>();
        GameController gameController = new GameController();

        TickActions tickActions = new TickActions.TickActionsBuilder().build();

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(tickActions, 0, 1000);

        // tickActions.run();

        //

        Hero troll = gameController.newMage("Troll");
        Hero rogie = gameController.newRogue("Rogie");

        tickActions.addAttack(troll, rogie);

        // troll.display();

    }
}
