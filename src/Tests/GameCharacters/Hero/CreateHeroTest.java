package Tests.GameCharacters.Hero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.EnumMap;

import org.junit.After;
import org.junit.Test;

import Game.GameCharacters.CharacterAttribute;
import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Hero.HeroType;

public class CreateHeroTest {
  String heroName = "TestHero name";
  Hero testHero = new Hero.HeroBuilder(heroName, HeroType.MAGE).build();

  public EnumMap<CharacterAttribute, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    EnumMap<CharacterAttribute, Integer> attributeMap = new EnumMap<>(CharacterAttribute.class);
    attributeMap.put(CharacterAttribute.STRENGTH, strength);
    attributeMap.put(CharacterAttribute.DEXTERITY, dexterity);
    attributeMap.put(CharacterAttribute.INTELLIGENCE, intelligence);
    return attributeMap;
  }

  //

  @After
  public void resetHero() {
    testHero = new Hero.HeroBuilder(heroName, HeroType.MAGE).build();
  }

  //

  @Test
  public void WhenHeroCreated_NameIsCorrect() {
    String heroNameFromNewHero = testHero.getName();
    assertEquals(heroName, heroNameFromNewHero);

    assertNotEquals("9823fsfdklj", heroNameFromNewHero);
  }

  @Test
  public void WhenHeroCreated_LevelIsOne() {
    assertEquals(1, testHero.getLevel());

    assertNotEquals(2, testHero.getLevel());
  }

  @Test
  public void GivenHeroType_WhenHeroCreated_HeroAttributesAreCorrect() {
    HeroType heroType = (HeroType) testHero.getCharacterType();

    switch (heroType) {
      case WARRIOR:
        assertEquals(createAttributeMap(5, 2, 1), testHero.getHeroAttributes());

        assertNotEquals(createAttributeMap(9, 9, 9), testHero.getHeroAttributes());
        break;
      case ROGUE:
        assertEquals(createAttributeMap(2, 6, 1), testHero.getHeroAttributes());

        assertNotEquals(createAttributeMap(9, 9, 9), testHero.getHeroAttributes());
        break;
      case RANGER:
        assertEquals(createAttributeMap(1, 7, 1), testHero.getHeroAttributes());

        assertNotEquals(createAttributeMap(9, 9, 9), testHero.getHeroAttributes());
        break;
      case MAGE:
        assertEquals(createAttributeMap(1, 1, 8), testHero.getHeroAttributes());

        assertNotEquals(createAttributeMap(9, 9, 9), testHero.getHeroAttributes());
        break;
    }
  }

}
