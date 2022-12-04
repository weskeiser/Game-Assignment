import ENUMS.HeroTypes;
import ENUMS.Weapons;
import Equipment.Weapon;
import Hero.Hero;
import Interfaces.Item;

public class App {
    public static void main(String[] args) throws Exception {
        Hero mando = new Hero.HeroBuilder("Mando", HeroTypes.WARRIOR).build();

        Item GreatAxe = new Weapon.WeaponBuilder(Weapons.GREATAXE).build();

        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);
        mando.loot(GreatAxe);

        // mando.insertIntoInventory(GreatAxe);
    }
}
