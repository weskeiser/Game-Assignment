import ENUMS.HeroTypes;
import ENUMS.Weapons;
import Equipment.Weapon;
import Hero.Hero;
import Interfaces.GameCharacter;
import Interfaces.Item;

public class App {
    public static void main(String[] args) throws Exception {
        GameCharacter mando = new Hero.HeroBuilder("Mando", HeroTypes.WARRIOR).build();
        GameCharacter trollekunstner = new Hero.HeroBuilder("Trollekunstner", HeroTypes.MAGE).build();

        Item GreatAxe = new Weapon.WeaponBuilder(Weapons.GREATAXE).build();

        mando.levelUp();
        mando.levelUp();

        mando.loot(GreatAxe);
        Weapon axe = (Weapon) mando.getInventoryItemByIdx(0);
        mando.wield(axe);
        // mando.wield(axe);
        // mando.wield(axe);
        mando.showEquippedItems();
        mando.showInventory();

        mando.addToInventory(GreatAxe);
        mando.addToInventory(GreatAxe);
        mando.showInventory();
        mando.dropItem(GreatAxe);
        mando.showInventory();

        mando.display();
        // mando.wield(GreatAxe);

    }
}
