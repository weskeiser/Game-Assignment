package Tests.GameCharacters.Hero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.EnumMap;

import org.junit.After;
import org.junit.Test;

import Components.GameCharacters.Hero.Hero;
import Components.GameCharacters.Hero.HeroType;
import Components.GameCharacters.Interfaces.CharacterAttribute;

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
        assertEquals(createAttributeMap(5, 2, 1), testHero.getCharacterAttributes());

        assertNotEquals(createAttributeMap(9, 9, 9), testHero.getCharacterAttributes());
        break;
      case ROGUE:
        assertEquals(createAttributeMap(2, 6, 1), testHero.getCharacterAttributes());

        assertNotEquals(createAttributeMap(9, 9, 9), testHero.getCharacterAttributes());
        break;
      case RANGER:
        assertEquals(createAttributeMap(1, 7, 1), testHero.getCharacterAttributes());

        assertNotEquals(createAttributeMap(9, 9, 9), testHero.getCharacterAttributes());
        break;
      case MAGE:
        assertEquals(createAttributeMap(1, 1, 8), testHero.getCharacterAttributes());

        assertNotEquals(createAttributeMap(9, 9, 9), testHero.getCharacterAttributes());
        break;
    }
  }

}
