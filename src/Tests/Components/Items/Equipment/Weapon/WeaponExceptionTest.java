package Tests.Components.Items.Equipment.Weapon;

import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import Game.Components.Exceptions.InvalidEquipmentException;
import Game.Components.GameCharacters.Hero.Hero;
import Game.Components.GameCharacters.Hero.HeroType;
import Game.Components.Items.Equipment.Weapon.Weapon;
import Game.Components.Items.Equipment.Weapon.WeaponItem;

@RunWith(Parameterized.class)
public class WeaponExceptionTest {

  private Hero testHero;

  private Weapon insufficientLevelWeapon;
  private Weapon wrongTypeWeapon;

  // Params for constructor
  @Parameterized.Parameters

  public static Collection<Object[]> testParams() {

    return Arrays.asList(
        new Object[][] {
            { HeroType.WARRIOR, WeaponItem.EXCALIBUR, WeaponItem.CLEANING_STAFF },
            { HeroType.ROGUE, WeaponItem.BLOODY_DAGGER, WeaponItem.CLEANING_STAFF },
            { HeroType.RANGER, WeaponItem.CROOKED_BOW, WeaponItem.CLEANING_STAFF },
            { HeroType.MAGE, WeaponItem.ROOT_WAND, WeaponItem.GREATAXE },
        });
  }

  // Constructor
  public WeaponExceptionTest(HeroType heroType, WeaponItem insufficientLevelWeapon, WeaponItem wrongTypeWeapon) {

    this.testHero = new Hero.HeroBuilder("TestHero Name", heroType).build();

    this.insufficientLevelWeapon = new Weapon.WeaponBuilder(insufficientLevelWeapon).build();
    this.wrongTypeWeapon = new Weapon.WeaponBuilder(wrongTypeWeapon).build();
  }

  @Test
  public void givenHeroType_whenInsufficientLevel_shouldThrowEquipmentException() {
    assertThrows(InvalidEquipmentException.class, () -> {
      testHero.addToInventory(insufficientLevelWeapon);
      testHero.equip(insufficientLevelWeapon);
    });
  }

  @Test
  public void givenHeroType_whenWrongType_shouldThrowEquipmentException() {
    assertThrows(InvalidEquipmentException.class, () -> {
      testHero.addToInventory(wrongTypeWeapon);
      testHero.equip(wrongTypeWeapon);
    });
  }

}
