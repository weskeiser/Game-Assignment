package Interfaces;

import java.util.EnumMap;
import java.util.EnumSet;

import ENUMS.ArmorTypes;
import ENUMS.HeroAttributes;
import ENUMS.WeaponTypes;

public interface HeroType {
  EnumMap<HeroAttributes, Integer> init();

  EnumMap<HeroAttributes, Integer> getLevelAttributes();

  EnumSet<WeaponTypes> getValidWeaponTypes();

  EnumSet<ArmorTypes> getValidArmorTypes();

  EnumMap<HeroAttributes, Integer> createAttributeMap(int strength, int dexterity, int intelligence);
}
