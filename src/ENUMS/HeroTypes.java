package ENUMS;

import java.util.EnumMap;

public enum HeroTypes {
  WARRIOR(5, 2, 1),
  ROGUE(2, 6, 1),
  RANGER(1, 7, 1),
  MAGE(1, 1, 8);

  private EnumMap<LevelAttributes, Integer> startingAttributes = new EnumMap<>(LevelAttributes.class);

  HeroTypes(int strength, int dexterity, int intelligence) {
    startingAttributes.put(LevelAttributes.STRENGTH, strength);
    startingAttributes.put(LevelAttributes.DEXTERITY, dexterity);
    startingAttributes.put(LevelAttributes.INTELLIGENCE, intelligence);
  }

  public EnumMap<LevelAttributes, Integer> getStartingAttributes() {
    return startingAttributes;
  }
}
