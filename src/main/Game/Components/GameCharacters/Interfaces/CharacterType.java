package main.Game.Components.GameCharacters.Interfaces;

import java.util.EnumSet;

import main.Game.Components.Items.Equipment.Armor.ArmorType;
import main.Game.Components.Items.Equipment.Weapon.WeaponType;

public interface CharacterType {
  public CharacterAttribute getAttackAttribute();

  // public EnumMap<CharacterAttribute, Integer> getLevelingAttributes();

  public EnumSet<WeaponType> getValidWeaponTypes();

  public EnumSet<ArmorType> getValidArmorTypes();

}
