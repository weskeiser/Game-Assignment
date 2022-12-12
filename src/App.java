import java.util.Timer;

import Adventure.Adventure;
import GameController.GameController;

public class App {
    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        GameController gameController = new GameController();

        Adventure adventure = new Adventure(gameController.newMage("Troll"));

        timer.scheduleAtFixedRate(adventure, 0, 1000);

        adventure.run();

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
