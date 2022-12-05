package Interfaces;

import java.util.EnumMap;
import java.util.EnumSet;

import ENUMS.ArmorTypes;
import ENUMS.HeroAttributes;
import ENUMS.WeaponTypes;

public interface HeroType {
  void init();

  EnumMap<HeroAttributes, Integer> createAttributeMap(int strength, int dexterity, int intelligence);

  EnumMap<HeroAttributes, Integer> getStartingAttributes();

  EnumMap<HeroAttributes, Integer> getLevelAttributes();

  EnumSet<WeaponTypes> getValidWeaponTypes();

  EnumSet<ArmorTypes> getValidArmorTypes();
}
