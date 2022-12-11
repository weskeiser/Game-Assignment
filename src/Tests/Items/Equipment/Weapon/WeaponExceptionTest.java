package Tests.Items.Equipment.Weapon;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

import Game.Exceptions.InvalidEquipmentException;
import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Hero.HeroType;
import Game.Items.Equipment.Weapon.Weapon;
import Game.Items.Equipment.Weapon.WeaponItem;

// NOTE: Inventory slot 0 is taken up by starter weapon.

public class WeaponExceptionTest {

  @Test
  public void givenHeroType_whenInsufficientLevel_shouldThrowEquipmentException() {
    Hero testHero = new Hero.HeroBuilder("Hero TestName", HeroType.MAGE).build();
    Weapon insufficientLevelWeapon = new Weapon.WeaponBuilder(WeaponItem.CLEANING_STAFF).build();

    assertThrows(InvalidEquipmentException.class, () -> {
      testHero.addToInventory(insufficientLevelWeapon);
      testHero.equip(1);
    });
  }

  @Test
  public void givenHeroType_whenWrongType_shouldThrowEquipmentException() {
    Hero testHero = new Hero.HeroBuilder("Hero TestName", HeroType.MAGE).build();
    Weapon wrongTypeWeapon = new Weapon.WeaponBuilder(WeaponItem.WOODEN_SWORD).build();

    assertThrows(InvalidEquipmentException.class, () -> {
      testHero.addToInventory(wrongTypeWeapon);
      testHero.equip(1);
    });
  }

}
