package Components.GameCharacters.Interfaces;

import java.util.EnumMap;
import java.util.EnumSet;

import Components.Items.Equipment.Armor.ArmorType;
import Components.Items.Equipment.Weapon.WeaponType;

public interface CharacterType {
  public CharacterAttribute getDamagingAttribute();

  public EnumMap<CharacterAttribute, Integer> getLevelingAttributes();

  public EnumSet<WeaponType> getValidWeaponTypes();

  public EnumSet<ArmorType> getValidArmorTypes();

}
