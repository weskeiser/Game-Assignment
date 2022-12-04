package Interfaces;

import java.util.EnumMap;
import java.util.EnumSet;
import ENUMS.ArmorTypes;
import ENUMS.LevelAttributes;
import ENUMS.WeaponTypes;

public interface GameCharacter {
  public String getName();

  public int getLevel();

  public void loot(Item item);

  public EnumSet<WeaponTypes> getValidWeaponTypes();

  public EnumSet<ArmorTypes> getValidArmorTypes();

  public EnumMap<LevelAttributes, Integer> getLevelAttributes();
}
