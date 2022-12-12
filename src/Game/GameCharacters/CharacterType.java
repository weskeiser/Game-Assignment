package Game.GameCharacters;

import java.util.EnumMap;
import java.util.EnumSet;

import Game.Items.Equipment.Armor.ArmorType;
import Game.Items.Equipment.Weapon.WeaponType;

public interface CharacterType {
  public CharacterAttribute getDamagingAttribute();

  public EnumMap<CharacterAttribute, Integer> getLevelingAttributes();

  public EnumSet<WeaponType> getValidWeaponTypes();

  public EnumSet<ArmorType> getValidArmorTypes();

}
