package ENUMS;

import java.util.EnumMap;

import Interfaces.HeroAttribute;

public enum HeroTypes implements HeroAttribute {
  WARRIOR(5, 2, 1),
  ROGUE(2, 6, 1),
  RANGER(1, 7, 1),
  MAGE(1, 1, 8);

  private EnumMap<HeroAttributes, Integer> startingAttributes = new EnumMap<>(HeroAttributes.class);

  HeroTypes(int strength, int dexterity, int intelligence) {
    startingAttributes.put(HeroAttributes.STRENGTH, strength);
    startingAttributes.put(HeroAttributes.DEXTERITY, dexterity);
    startingAttributes.put(HeroAttributes.INTELLIGENCE, intelligence);
  }

  public EnumMap<HeroAttributes, Integer> getStartingAttributes() {
    return startingAttributes;
  }
}
