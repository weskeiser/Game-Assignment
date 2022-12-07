import Game.Game;
import Game.GameCharacters.GameCharacter;
import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Hero.HeroType;
import Game.Items.Lootable;
import Game.Items.Equipment.Armor.Armor;
import Game.Items.Equipment.Armor.ArmorItems;
import Game.Items.Equipment.Weapon.Weapon;
import Game.Items.Equipment.Weapon.Weapons;

public class App {
    public static void main(String[] args) throws Exception {
        GameCharacter mando = new Hero.HeroBuilder("Mando", HeroType.WARRIOR).build();

        Lootable GreatAxe = new Weapon.WeaponBuilder(Weapons.GREATAXE).build();

        mando.levelUp();
        mando.levelUp();

        mando.loot(GreatAxe);
        Lootable axe = (Weapon) mando.getInventoryItemByIdx(0);
        Lootable royalMail = new Armor.ArmorBuilder(ArmorItems.ROYAL_MAIL).build();
        mando.loot(axe);
        mando.showInventory();

        System.out.println();
        GameCharacter troll = Game.newWarrior("Troll");

    }
}
