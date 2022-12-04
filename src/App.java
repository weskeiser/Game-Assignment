import ENUMS.HeroTypes;
import ENUMS.Weapons;
import Equipment.Weapon;
import Hero.Hero;
import Interfaces.GameCharacter;
import Interfaces.Item;

public class App {
    public static void main(String[] args) throws Exception {
        GameCharacter mando = new Hero.HeroBuilder("Mando", HeroTypes.WARRIOR).build();

        System.out.println(

                mando.getHeroAttributes());

        // Item GreatAxe = new Weapon.WeaponBuilder(Weapons.GREATAXE).build();

    }
}
