package test.Game.Components.GameCharacters.Hero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import main.Game.Components.Exceptions.InventoryException;
import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.GameCharacters.Hero.HeroType;
import main.Game.Components.Items.Equipment.Weapon.Weapon;

@RunWith(Parameterized.class)
public class CreateHeroTest {
  private int HERO_STARTING_HEALTH = 10;
  private int HERO_STARTING_EXPERIENCE = 0;
  private int HERO_STARTING_LEVEL = 1;

  private Hero testHero;
  private HeroType heroType;

  private String expectedHeroName = "TestHero Name";

  // Params for constructor
  @Parameterized.Parameters

  public static Collection<HeroType> testParams() {

    return Arrays.asList(
        HeroType.WARRIOR,
        HeroType.ROGUE,
        HeroType.RANGER,
        HeroType.MAGE);
  }

  // Constructor
  public CreateHeroTest(HeroType heroType) {

    this.testHero = new Hero.HeroBuilder(expectedHeroName, heroType).build();
    this.heroType = heroType;
  }

  //

  @Test
  public void CreateHero_GetName_MatchesInput() {
    assertEquals(expectedHeroName, testHero.getName());
  }

  @Test
  public void CreateHero_GetHealth_HealthIsZero() {
    assertEquals(HERO_STARTING_HEALTH, testHero.getHealth(), 0);
  }

  @Test
  public void CreateHero_GetLevel_LevelIsZero() {
    assertEquals(HERO_STARTING_LEVEL, testHero.getLevel(), 0);
  }

  @Test
  public void CreateHero_GetExperience_ExperieceIsZero() {
    assertEquals(HERO_STARTING_EXPERIENCE, testHero.getExperience(), 0);
  }

  @Test
  public void CreateHero_EquipFromFirstInvSlot_IsStartingWeapon() {
    Weapon firstInventorySlotItem = null;

    try {
      firstInventorySlotItem = (Weapon) testHero.findInventoryItem(0);
    } catch (InventoryException err) {
      fail();
    }

    assertEquals(heroType.getStarterWeapon(), firstInventorySlotItem.getWeapon());
  }

  @Test
  public void CreateHero_GetCharacterAttributes_MatchesGivenHero() {
    assertEquals(heroType.init(), testHero.getCharacterAttributes());
  }

}
