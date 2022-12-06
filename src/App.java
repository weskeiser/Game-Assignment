import Items.Item;
import Items.Equipment.EquipmentSlot;
import Items.Equipment.Armor.Armor;
import Items.Equipment.Armor.ArmorItems;
import Items.Equipment.Weapon.Weapon;
import Items.Equipment.Weapon.Weapons;
import GameCharacters.GameCharacter;
import GameCharacters.Hero.Hero;
import GameCharacters.Hero.HeroType;

public class App {
    public static void main(String[] args) throws Exception {
        GameCharacter mando = new Hero.HeroBuilder("Mando", HeroType.WARRIOR).build();
        GameCharacter trollekunstner = new Hero.HeroBuilder("Trollekunstner", HeroType.MAGE).build();

        Item GreatAxe = new Weapon.WeaponBuilder(Weapons.GREATAXE).build();

        mando.levelUp();
        mando.levelUp();

        mando.loot(GreatAxe);
        Weapon axe = (Weapon) mando.getInventoryItemByIdx(0);
        Armor royalMail = new Armor.ArmorBuilder(ArmorItems.ROYAL_MAIL).build();
        mando.equip(axe);
        mando.showInventory();

        mando.addToInventory(GreatAxe);
        mando.addToInventory(GreatAxe);
        mando.dropItem(GreatAxe);

        mando.display();
        axe.inspect();
        mando.attack(trollekunstner);
        royalMail.inspect();
        mando.loot(royalMail);
        mando.equip(royalMail);
        mando.unEquip(EquipmentSlot.WEAPON);
        mando.unEquip(EquipmentSlot.WEAPON);
        // mando.wield(GreatAxe);

    }
}
