package test.Game.Components.GameCharacters.Hero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.GameCharacters.Hero.HeroType;
import main.Game.Components.GameCharacters.Interfaces.CharacterAttribute;
import main.Game.Components.Items.Equipment.EquipmentManager;
import main.Game.Components.Items.Equipment.Weapon.Weapon;
import main.Game.Components.Items.Equipment.Weapon.WeaponItem;

@RunWith(Parameterized.class)
public class HeroDamageTest {
  private Hero testHero;

  Weapon heroWeaponOne;
  Weapon heroWeaponTwo;

  // Params for constructor
  @Parameterized.Parameters
  public static Collection<Object[]> testParams() {

    return Arrays.asList(
        new Object[][] {
            { HeroType.WARRIOR, WeaponItem.GREATAXE, WeaponItem.EXCALIBUR },
            { HeroType.ROGUE, WeaponItem.GUARD_RAIL, WeaponItem.BLOODY_DAGGER },
            { HeroType.RANGER, WeaponItem.CROOKED_BOW, WeaponItem.TIED_BOW },
            { HeroType.MAGE, WeaponItem.CRACKED_WAND, WeaponItem.CLEANING_STAFF },
        });
  }

  // Constructor
  public HeroDamageTest(HeroType heroType, WeaponItem heroWeaponOne, WeaponItem heroWeaponTwo) {

    this.testHero = new Hero.HeroBuilder("TestHero Name", heroType).build();
    this.heroWeaponOne = new Weapon.WeaponBuilder(heroWeaponOne).build();
    this.heroWeaponTwo = new Weapon.WeaponBuilder(heroWeaponTwo).build();

    // Level up before each test
    for (int i = 0; i < 22; i++) {
      testHero.levelUp();
    }
  }

  //

  @Test

  public void EquipNoWeapon_GetMaxHit_IsCorrectNumber() {
    var barehandWeaponDamage = 1;
    var strengthAttribute = testHero.getCharacterAttributes().get(CharacterAttribute.STRENGTH);
    var expectedMaxHit = barehandWeaponDamage * (1 + strengthAttribute / 100);

    assertEquals(expectedMaxHit, testHero.getMaxHit(), 0);
  }

  @Test

  public void EquipWeapon_GetMaxHit_IsCorrectNumber() {
    try {
      testHero.addToInventory(heroWeaponOne);
      testHero.equip(heroWeaponOne);
    } catch (Throwable err) {
      fail();
    }

    Optional<Weapon> equippedWeapon = ((EquipmentManager) testHero).getEquippedWeapon();

    if (equippedWeapon.isEmpty())
      fail();

    var weaponDamage = equippedWeapon.map(Weapon::getDamageMultiplier).orElse(1.0);
    var strengthAttribute = testHero.getCharacterAttributes().get(CharacterAttribute.STRENGTH);
    var expectedMaxHit = weaponDamage * (1 + strengthAttribute / 100);

    assertEquals(expectedMaxHit, testHero.getMaxHit(), 0);
  }

  @Test

  public void ReplaceEquippedWeapon_GetMaxHit_IsCorrectNumber() {
    try {
      testHero.addToInventory(heroWeaponOne);
      testHero.equip(heroWeaponOne);
      testHero.addToInventory(heroWeaponTwo);
      testHero.equip(heroWeaponTwo);
    } catch (Throwable err) {
      fail();
    }

    Optional<Weapon> equippedWeapon = ((EquipmentManager) testHero).getEquippedWeapon();

    if (equippedWeapon.isEmpty())
      fail();

    var weaponDamage = equippedWeapon.map(Weapon::getDamageMultiplier).orElse(1.0);
    var strengthAttribute = testHero.getCharacterAttributes().get(CharacterAttribute.STRENGTH);
    var expectedMaxHit = weaponDamage * (1 + strengthAttribute / 100);

    assertEquals(expectedMaxHit, testHero.getMaxHit(), 0);
  }

}
