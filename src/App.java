import Game.Game;
import Game.GameCharacters.GameCharacter;
import Game.GameCharacters.Remains;
import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Hero.HeroType;
import Game.Items.Lootable;
import Game.Items.Equipment.Armor.Armor;
import Game.Items.Equipment.Armor.ArmorItems;
import Game.Items.Equipment.Weapon.Weapon;
import Game.Items.Equipment.Weapon.Weapons;

public class App {
    public static void main(String[] args) throws Exception {
        GameCharacter troll = Game.newMage("Troll");

        troll.levelUp();
        troll.levelUp();

        troll.display();

    }
}
