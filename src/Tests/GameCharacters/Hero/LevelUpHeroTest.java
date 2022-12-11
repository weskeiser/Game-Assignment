package Tests.GameCharacters.Hero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.EnumMap;
import java.util.Map;

import org.junit.Test;

import Game.GameCharacters.CharacterAttribute;
import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Hero.HeroType;

public class LevelUpHeroTest {

  @Test
  public void givenWarrior_whenLevelUp_levelsAreCorrent() {
    Hero warrior = new Hero.HeroBuilder("Hero TestName", HeroType.WARRIOR).build();
    levelUpCorrectlyTest(warrior);
  }

  @Test
  public void givenRogue_whenLevelUp_levelsAreCorrent() {
    Hero rogue = new Hero.HeroBuilder("Hero TestName", HeroType.ROGUE).build();
    levelUpCorrectlyTest(rogue);
  }

  @Test
  public void givenRanger_whenLevelUp_levelsAreCorrent() {
    Hero ranger = new Hero.HeroBuilder("Hero TestName", HeroType.RANGER).build();
    levelUpCorrectlyTest(ranger);
  }

  @Test
  public void givenMage_whenLevelUp_levelsAreCorrent() {
    Hero mage = new Hero.HeroBuilder("Hero TestName", HeroType.MAGE).build();
    levelUpCorrectlyTest(mage);
  }

  public void levelUpCorrectlyTest(Hero testHero) {
    HeroType heroType = (HeroType) testHero.getCharacterType();

    // At level 1
    assertEquals(1, testHero.getLevel());

    assertNotEquals(2, testHero.getLevel());

    switch (heroType) {
      case WARRIOR:
        assertEquals(createAttributeMap(5, 2, 1), testHero.getHeroAttributes());
        assertNotEquals(createAttributeMap(8, 4, 2), testHero.getHeroAttributes());
        break;
      case ROGUE:
        assertEquals(createAttributeMap(2, 6, 1), testHero.getHeroAttributes());
        assertNotEquals(createAttributeMap(3, 10, 2), testHero.getHeroAttributes());
        break;
      case RANGER:
        assertEquals(createAttributeMap(1, 7, 1), testHero.getHeroAttributes());
        assertNotEquals(createAttributeMap(2, 12, 2), testHero.getHeroAttributes());
        break;
      case MAGE:
        assertEquals(createAttributeMap(1, 1, 8), testHero.getHeroAttributes());
        assertNotEquals(createAttributeMap(2, 2, 13), testHero.getHeroAttributes());
        break;
    }

    // At level 2
    testHero.levelUp();
    assertEquals(2, testHero.getLevel());

    assertNotEquals(1, testHero.getLevel());
    assertNotEquals(3, testHero.getLevel());

    switch (heroType) {
      case WARRIOR:
        assertEquals(createAttributeMap(8, 4, 2), testHero.getHeroAttributes());

        break;
      case ROGUE:
        assertEquals(createAttributeMap(3, 10, 2), testHero.getHeroAttributes());
        break;
      case RANGER:
        assertEquals(createAttributeMap(2, 12, 2), testHero.getHeroAttributes());
        break;
      case MAGE:
        assertEquals(createAttributeMap(2, 2, 13), testHero.getHeroAttributes());
        break;
    }

    // At level 3
    testHero.levelUp();
    assertEquals(3, testHero.getLevel());

    assertNotEquals(2, testHero.getLevel());
    assertNotEquals(4, testHero.getLevel());

    switch (heroType) {
      case WARRIOR:
        assertEquals(createAttributeMap(11, 6, 3), testHero.getHeroAttributes());
        break;
      case ROGUE:
        assertEquals(createAttributeMap(4, 14, 3), testHero.getHeroAttributes());
        break;
      case RANGER:
        assertEquals(createAttributeMap(3, 17, 3), testHero.getHeroAttributes());
        break;
      case MAGE:
        assertEquals(createAttributeMap(3, 3, 18), testHero.getHeroAttributes());
        break;
    }

  }

  public EnumMap<CharacterAttribute, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    return new EnumMap<>(Map.ofEntries(
        Map.entry(CharacterAttribute.STRENGTH, strength),
        Map.entry(CharacterAttribute.DEXTERITY, dexterity),
        Map.entry(CharacterAttribute.INTELLIGENCE, intelligence)));
  }
}
