package Tests.GameCharacters.Hero;

import static org.junit.Assert.assertEquals;

import java.util.EnumMap;

import org.junit.Test;

import Game.Game;
import Game.GameCharacters.CharacterAttribute;
import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Hero.HeroType;

public class LevelUp {
  // Hero testHero = Game.newMage("TestHero Name");
  // Hero testHero = Game.newWarrior("TestHero Name");
  // Hero testHero = Game.newRogue("TestHero Name");
  Hero testHero = Game.newRanger("TestHero Name");

  @Test
  public void levelUpCorrectly() {
    HeroType heroType = (HeroType) testHero.getCharacterType();

    // At level 1
    assertEquals(1, testHero.getLevel());

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

  public EnumMap<CharacterAttribute, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    EnumMap<CharacterAttribute, Integer> attributeMap = new EnumMap<>(CharacterAttribute.class);
    attributeMap.put(CharacterAttribute.STRENGTH, strength);
    attributeMap.put(CharacterAttribute.DEXTERITY, dexterity);
    attributeMap.put(CharacterAttribute.INTELLIGENCE, intelligence);
    return attributeMap;
  }

}
