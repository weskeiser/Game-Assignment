package Interfaces;

import java.util.EnumMap;
import java.util.EnumSet;
import ENUMS.ArmorTypes;
import ENUMS.HeroAttributes;
import ENUMS.WeaponTypes;

public interface GameCharacter {

  String name = null;

  String getName();

  int getLevel();

  void loot(Item item);

  EnumSet<WeaponTypes> getValidWeaponTypes();

  EnumSet<ArmorTypes> getValidArmorTypes();

  EnumMap<HeroAttributes, Integer> getHeroAttributes();
}
