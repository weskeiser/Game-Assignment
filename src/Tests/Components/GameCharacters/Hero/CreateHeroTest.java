package Tests.Components.GameCharacters.Hero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import Game.Components.Exceptions.InventoryException;
import Game.Components.GameCharacters.Hero.Hero;
import Game.Components.GameCharacters.Hero.HeroType;
import Game.Components.GameCharacters.Interfaces.AttributeManager;
import Game.Components.GameCharacters.Interfaces.CharacterAttribute;
import Game.Components.Items.Equipment.Weapon.Weapon;
import Game.Components.Items.Equipment.Weapon.WeaponItem;

@RunWith(Parameterized.class)
public class CreateHeroTest {
  private Hero testHero;

  private String expectedHeroName = "TestHero Name";
  private WeaponItem expectedStartingWeapon;
  private EnumMap<CharacterAttribute, Integer> expectedStartingAttributes;

  //

  @Parameterized.Parameters
  public static Collection<Object[]> testParams() {
    return Arrays.asList(
        new Object[][] {
            { HeroType.WARRIOR, WeaponItem.WOODEN_SWORD, AttributeManager.newAttributeMap(5, 2, 1) },
            { HeroType.ROGUE, WeaponItem.WOODEN_SWORD, AttributeManager.newAttributeMap(2, 6, 1) },
            { HeroType.RANGER, WeaponItem.MAKESHIFT_BOW, AttributeManager.newAttributeMap(1, 7, 1) },
            { HeroType.MAGE, WeaponItem.CRACKED_WAND, AttributeManager.newAttributeMap(1, 1, 8) } });
  }

  public CreateHeroTest(HeroType heroType, WeaponItem expectedStartingWeapon,
      EnumMap<CharacterAttribute, Integer> expectedStartingAttributes) {

    this.testHero = new Hero.HeroBuilder(expectedHeroName, heroType).build();
    this.expectedStartingWeapon = expectedStartingWeapon;
    this.expectedStartingAttributes = expectedStartingAttributes;
  }

  //

  @Test
  public void NewHero_GetName_IsCorrect() {
    assertEquals(expectedHeroName, testHero.getName());
  }

  @Test
  public void NewHero_GetHealth_HealthIsZero() {
    assertEquals(Hero.HERO_STARTING_HEALTH, testHero.getHealth(), 0);
  }

  @Test
  public void NewHero_GetLevel_LevelIsZero() {
    assertEquals(Hero.HERO_STARTING_LEVEL, testHero.getLevel(), 0);
  }

  @Test
  public void NewHero_GetExperience_ExperieceIsZero() {
    assertEquals(Hero.HERO_STARTING_EXPERIENCE, testHero.getExperience(), 0);
  }

  @Test
  public void NewHero_EquipFromFirstInvSlot_IsStartingWeapon() {
    Weapon firstInventorySlotItem = null;

    try {
      firstInventorySlotItem = (Weapon) testHero.findInventoryItem(0);
    } catch (InventoryException err) {
      fail();
    }

    assertEquals(expectedStartingWeapon, firstInventorySlotItem.getWeapon());
  }

  @Test
  public void NewHero_GetCharacterAttributes_IsCorrect() {
    assertEquals(expectedStartingAttributes, testHero.getCharacterAttributes());
  }

}
