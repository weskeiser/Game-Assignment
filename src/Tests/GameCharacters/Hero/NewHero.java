package Tests.GameCharacters.Hero;

import static org.junit.Assert.assertEquals;

import java.util.EnumMap;

import org.junit.Test;

import Game.Game;
import Game.GameCharacters.Hero.HeroType;
import Game.GameCharacters.CharacterAttribute;
import Game.GameCharacters.Hero.Hero;

public class NewHero {
  String heroName = "TestHero name";
  Hero testHero = Game.newMage(heroName);

  @Test
  public void heroNameIsCorrect() {
    String heroNameFromNewHero = testHero.getName();
    assertEquals(heroName, heroNameFromNewHero);
  }

  @Test
  public void startLevelIs1() {
    assertEquals(1, testHero.getLevel());
  }

  @Test
  public void correctHeroStartingAttributes() {
    HeroType heroType = (HeroType) testHero.getCharacterType();

    switch (heroType) {
      case WARRIOR:
        assertEquals(createAttributeMap(5, 2, 1), testHero.getHeroAttributes());
        break;
      case ROGUE:
        assertEquals(createAttributeMap(2, 6, 1), testHero.getHeroAttributes());
        break;
      case RANGER:
        assertEquals(createAttributeMap(1, 7, 1), testHero.getHeroAttributes());
        break;
      case MAGE:
        assertEquals(createAttributeMap(1, 1, 8), testHero.getHeroAttributes());
        break;
    }
  }

  public EnumMap<CharacterAttribute, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    EnumMap<CharacterAttribute, Integer> attributeMap = new EnumMap<>(CharacterAttribute.class);
    attributeMap.put(CharacterAttribute.STRENGTH, strength);
    attributeMap.put(CharacterAttribute.DEXTERITY, dexterity);
    attributeMap.put(CharacterAttribute.INTELLIGENCE, intelligence);
    return attributeMap;
  }

}
