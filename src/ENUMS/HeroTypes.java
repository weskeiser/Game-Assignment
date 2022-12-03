package ENUMS;

import java.util.EnumMap;

public enum HeroTypes {
  WARRIOR(5, 2, 1),
  ROGUE(5, 2, 1),
  RANGER(5, 2, 1),
  MAGE(5, 2, 1);

  private EnumMap<LevelAttributes, Integer> levelAttributes = new EnumMap<>(LevelAttributes.class);

  HeroTypes(int strength, int dexterity, int intelligence) {
    levelAttributes.put(LevelAttributes.STRENGTH, strength);
    levelAttributes.put(LevelAttributes.DEXTERITY, dexterity);
    levelAttributes.put(LevelAttributes.INTELLIGENCE, intelligence);
  }

  public EnumMap<LevelAttributes, Integer> getLevelAttributes() {
    return levelAttributes;
  }
}
