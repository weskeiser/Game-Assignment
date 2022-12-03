import ENUMS.HeroTypes;
import ENUMS.Weapons;
import Equipment.Weapon;
import Hero.Hero;

public class App {
    public static void main(String[] args) throws Exception {
        Hero mando = new Hero.HeroBuilder("Mando", HeroTypes.WARRIOR).build();

        System.out.println(mando.getName());
        System.out.println(mando.getLevelAttributes());
        System.out.println(mando.getValidWeaponTypes());
        System.out.println(mando.getValidArmorTypes());

        Weapon GreatAxe = new Weapon.WeaponBuilder(Weapons.GREATAXE).build();
        System.out.println(GreatAxe.inspect());

    }
}
