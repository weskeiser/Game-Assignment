package Tests.GameCharacters.Hero;

import static org.junit.Assert.assertEquals;

import java.util.EnumMap;
import java.util.Map;

import org.junit.Test;

import Game.GameCharacters.CharacterAttribute;
import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Hero.HeroType;

public class LevelUpHeroTest {
  Hero warrior = new Hero.HeroBuilder("Hero TestName", HeroType.WARRIOR).build();

  private final EnumMap<CharacterAttribute, Integer> WARRIOR_STARTING_ATTRIBUTES = createAttributeMap(5, 2, 1);
  private final EnumMap<CharacterAttribute, Integer> ROGUE_STARTING_ATTRIBUTES = createAttributeMap(2, 6, 1);
  private final EnumMap<CharacterAttribute, Integer> RANGER_STARTING_ATTRIBUTES = createAttributeMap(1, 7, 1);
  private final EnumMap<CharacterAttribute, Integer> MAGE_STARTING_ATTRIBUTES = createAttributeMap(1, 1, 8);

  EnumMap<CharacterAttribute, Integer> WARRIOR_LEVELING_ATTRIBUTES = HeroType.WARRIOR.getLevelingAttributes();
  EnumMap<CharacterAttribute, Integer> ROGUE_LEVELING_ATTRIBUTES = HeroType.ROGUE.getLevelingAttributes();
  EnumMap<CharacterAttribute, Integer> RANGER_LEVELING_ATTRIBUTES = HeroType.RANGER.getLevelingAttributes();
  EnumMap<CharacterAttribute, Integer> MAGE_LEVELING_ATTRIBUTES = HeroType.MAGE.getLevelingAttributes();

  public EnumMap<CharacterAttribute, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    return new EnumMap<>(Map.ofEntries(
        Map.entry(CharacterAttribute.STRENGTH, strength),
        Map.entry(CharacterAttribute.DEXTERITY, dexterity),
        Map.entry(CharacterAttribute.INTELLIGENCE, intelligence)));
  }

  public void levelUpCorrectlyTest(Hero testHero) {
    HeroType heroType = (HeroType) testHero.getCharacterType();

    // At level 1
    assertEquals(1, testHero.getLevel());

    switch (heroType) {
      case WARRIOR:
        assertEquals(WARRIOR_STARTING_ATTRIBUTES, testHero.getHeroAttributes());
        break;
      case ROGUE:
        assertEquals(ROGUE_STARTING_ATTRIBUTES, testHero.getHeroAttributes());
        break;
      case RANGER:
        assertEquals(RANGER_STARTING_ATTRIBUTES, testHero.getHeroAttributes());
        break;
      case MAGE:
        assertEquals(MAGE_STARTING_ATTRIBUTES, testHero.getHeroAttributes());
        break;
    }

    // At level 2
    testHero.levelUp();
    assertEquals(2, testHero.getLevel());

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

  @Test
  public void GivenWarrior_WhenLevelUp_LevelsAreCorrent() {
    Hero warrior = new Hero.HeroBuilder("Hero TestName", HeroType.WARRIOR).build();
    levelUpCorrectlyTest(warrior);
  }

  @Test
  public void GivenRogue_WhenLevelUp_LevelsAreCorrent() {
    Hero rogue = new Hero.HeroBuilder("Hero TestName", HeroType.ROGUE).build();
    levelUpCorrectlyTest(rogue);
  }

  @Test
  public void GivenRanger_WhenLevelUp_LevelsAreCorrent() {
    Hero ranger = new Hero.HeroBuilder("Hero TestName", HeroType.RANGER).build();
    levelUpCorrectlyTest(ranger);
  }

  @Test
  public void GivenMage_WhenLevelUp_LevelsAreCorrent() {
    Hero mage = new Hero.HeroBuilder("Hero TestName", HeroType.MAGE).build();
    levelUpCorrectlyTest(mage);
  }

}
