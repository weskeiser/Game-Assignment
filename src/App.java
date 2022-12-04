import ENUMS.HeroTypes;
import Hero.Hero;
import Interfaces.GameCharacter;

public class App {
    public static void main(String[] args) throws Exception {
        GameCharacter mando = new Hero.HeroBuilder("Mando", HeroTypes.WARRIOR).build();

        System.out.println(mando.getHeroAttributes());
        System.out.println(mando.getValidArmorTypes());
        System.out.println(mando.getValidWeaponTypes());

        // Item GreatAxe = new Weapon.WeaponBuilder(Weapons.GREATAXE).build();

    }
}
