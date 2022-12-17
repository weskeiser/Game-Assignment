package Tests.Components.GameCharacters.Hero;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import Game.Components.GameCharacters.Hero.Hero;
import Game.Components.GameCharacters.Hero.HeroType;

@RunWith(Parameterized.class)
public class LevelUpHeroTest {

  private Hero testHero;

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
  public LevelUpHeroTest(HeroType heroType) {
    this.testHero = new Hero.HeroBuilder("TestHero Name", heroType).build();
  }

  @Test
  public void LevelsAreCorrectWhenLevelingUp() {
    var heroType = (HeroType) testHero.getCharacterType();
    var heroAttributes = heroType.init();
    var levelingAttributes = heroType.getLevelingAttributes();
    var initialHealth = 10;

    // At level 1

    assertEquals(1, testHero.getLevel());
    assertEquals(initialHealth, testHero.getHealth(), 0);
    assertEquals(heroAttributes, testHero.getCharacterAttributes());

    // At level 2

    testHero.levelUp();

    heroAttributes.replaceAll((heroAttribute, value) -> value + levelingAttributes.get(heroAttribute));

    assertEquals(2, testHero.getLevel());
    assertEquals(initialHealth + 2, testHero.getHealth(), 0);
    assertEquals(heroAttributes, testHero.getCharacterAttributes());

    // At level 3

    testHero.levelUp();

    heroAttributes.replaceAll((heroAttribute, value) -> value + levelingAttributes.get(heroAttribute));

    assertEquals(3, testHero.getLevel());
    assertEquals(initialHealth + 4, testHero.getHealth(), 0);
    assertEquals(heroAttributes, testHero.getCharacterAttributes());
  }
}
