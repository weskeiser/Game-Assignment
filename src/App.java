import Game.GameCharacters.Hero.Hero;
import Game.Items.Equipment.Weapon.Weapon;
import Game.Items.Equipment.Weapon.WeaponItem;
import GameController.GameController;

public class App {
    public static void main(String[] args) throws Exception {
        GameController game = new GameController();

        Hero troll = game.newMage("Troll");
        Weapon testWeapon = new Weapon.WeaponBuilder(WeaponItem.CROOKED_BOW).build();

        // Armor royalMail = new Armor.ArmorBuilder(Mail.ROYAL_MAIL).build();
        // System.out.println(royalMail);

        // troll.showInventory();
        // troll.showEquippedItems();

        // troll.equip(0);

        // troll.showInventory();
        // troll.showEquippedItems();

        // troll.unEquip(EquipmentSlot.WEAPON);

        // troll.showInventory();
        // troll.showEquippedItems();

        // troll.equip(0);

        // troll.showInventory();
        // troll.showEquippedItems();

        // troll.unEquip(EquipmentSlot.WEAPON);

        // troll.showInventory();
        // troll.showEquippedItems();

        // troll.getTotalAttributes();
        // troll.display();

        // troll.addToInventory(testWeapon);
        // troll.equip(1);

        troll.display();

    }
}
