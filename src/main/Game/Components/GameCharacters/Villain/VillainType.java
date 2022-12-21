package main.Game.Components.GameCharacters.Villain;

import java.util.EnumMap;
import java.util.EnumSet;

import main.Game.Components.GameCharacters.Hero.CharacterInitiator;
import main.Game.Components.GameCharacters.Interfaces.*;
import main.Game.Components.Items.Equipment.Armor.ArmorType;
import main.Game.Components.Items.Equipment.Weapon.WeaponType;

public enum VillainType implements CharacterType, CharacterInitiator {
  SKELESPEAR() {
    public EnumMap<CharacterAttribute, Integer> init() {

      attackAttribute = CharacterAttribute.STRENGTH;
      return AttributeManager.newAttributeMap(5, 2, 1);
    }
  };

  CharacterAttribute attackAttribute;

  EnumSet<WeaponType> validWeaponTypes = EnumSet.allOf(WeaponType.class);
  EnumSet<ArmorType> validArmorTypes = EnumSet.allOf(ArmorType.class);;

  public CharacterAttribute getAttackAttribute() {
    return attackAttribute;
  }

  public EnumSet<WeaponType> getValidWeaponTypes() {
    return validWeaponTypes;
  }

  public EnumSet<ArmorType> getValidArmorTypes() {
    return validArmorTypes;
  }

}
