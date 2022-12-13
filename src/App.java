import java.util.*;

import Game.GameCharacters.Interfaces.GameCharacter;
import Game.GameCharacters.Remains.Remains;
import GameController.GameController;

public class App {
    public static void main(String[] args) throws Exception {
        Map<GameCharacter, Remains> killedCharacters = new HashMap<>();

        Timer timer = new Timer();
        GameController gameController = new GameController();

        // CharacterStatus adventure = new
        // CharacterStatus(gameController.newMage("Troll"));

        // timer.scheduleAtFixedRate(adventure, 0, 1000);

        // adventure.run();

        //

        // Hero troll = gameController.newMage("Troll");
        // Weapon testWeapon = new Weapon.WeaponBuilder(WeaponItem.CROOKED_BOW).build();
        // Armor royalMail = new Armor.ArmorBuilder(Mail.ROYAL_MAIL).build();

        // //

        // Hero rogie = gameController.newRogue("Rogie");
        // gameController.combat(troll, rogie);
        // gameController.combat(rogie, troll);
        // troll.showHealth();
        // // troll.display();
        // // troll.finalBlow(rogie);
        // try {
        // troll.equip(0);
        // } catch (Throwable err) {

        // }
        // troll.display();

    }
}
