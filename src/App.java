import Game.Game;
import Game.GameCharacters.Hero.Hero;
import Game.Items.Equipment.EquipmentSlot;

public class App {
    public static void main(String[] args) throws Exception {
        Hero troll = Game.newMage("Troll");

        // Armor royalMail = new Armor.ArmorBuilder(Mail.ROYAL_MAIL).build();
        // System.out.println(royalMail);

        troll.showInventory();
        troll.showEquippedItems();

        troll.equip(0);

        troll.showInventory();
        troll.showEquippedItems();

        troll.unEquip(EquipmentSlot.WEAPON);

        troll.showInventory();
        troll.showEquippedItems();

        troll.equip(0);

        troll.showInventory();
        troll.showEquippedItems();

        troll.unEquip(EquipmentSlot.WEAPON);

        troll.showInventory();
        troll.showEquippedItems();

        troll.getTotalAttributes();

    }
}
