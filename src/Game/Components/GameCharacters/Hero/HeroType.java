package Game.Components.GameCharacters.Hero;

import java.util.EnumMap;
import java.util.EnumSet;

import Game.Components.GameCharacters.Interfaces.*;
import Game.Components.Items.Equipment.Armor.ArmorType;
import Game.Components.Items.Equipment.Weapon.WeaponItem;
import Game.Components.Items.Equipment.Weapon.WeaponType;

public enum HeroType implements CharacterType, HeroInitiator {
  WARRIOR() {
    public EnumMap<CharacterAttribute, Integer> init() {
      levelingAttributes = AttributeManager.newAttributeMap(3, 2, 1);
      validWeaponTypes = EnumSet.of(WeaponType.AXE, WeaponType.HAMMER, WeaponType.SWORD);
      validArmorTypes = EnumSet.of(ArmorType.PLATE, ArmorType.MAIL);
      attackAttribute = CharacterAttribute.STRENGTH;
      starterWeapon = WeaponItem.WOODEN_SWORD;
      return AttributeManager.newAttributeMap(5, 2, 1);
    }
  },
  ROGUE {
    public EnumMap<CharacterAttribute, Integer> init() {
      levelingAttributes = AttributeManager.newAttributeMap(1, 4, 1);
      validWeaponTypes = EnumSet.of(WeaponType.DAGGER, WeaponType.SWORD);
      validArmorTypes = EnumSet.of(ArmorType.LEATHER, ArmorType.MAIL);
      attackAttribute = CharacterAttribute.DEXTERITY;
      starterWeapon = WeaponItem.WOODEN_SWORD;
      return AttributeManager.newAttributeMap(2, 6, 1);
    }
  },
  RANGER {
    public EnumMap<CharacterAttribute, Integer> init() {
      levelingAttributes = AttributeManager.newAttributeMap(1, 5, 1);
      validWeaponTypes = EnumSet.of(WeaponType.BOW);
      validArmorTypes = EnumSet.of(ArmorType.LEATHER, ArmorType.MAIL);
      attackAttribute = CharacterAttribute.DEXTERITY;
      starterWeapon = WeaponItem.MAKESHIFT_BOW;
      return AttributeManager.newAttributeMap(1, 7, 1);
    }
  },
  MAGE {
    public EnumMap<CharacterAttribute, Integer> init() {
      levelingAttributes = AttributeManager.newAttributeMap(1, 1, 5);
      validWeaponTypes = EnumSet.of(WeaponType.STAFF, WeaponType.WAND);
      validArmorTypes = EnumSet.of(ArmorType.CLOTH);
      attackAttribute = CharacterAttribute.INTELLIGENCE;
      starterWeapon = WeaponItem.CRACKED_WAND;
      return AttributeManager.newAttributeMap(1, 1, 8);

    }
  };

  EnumMap<CharacterAttribute, Integer> levelingAttributes;
  EnumSet<WeaponType> validWeaponTypes;
  EnumSet<ArmorType> validArmorTypes;
  CharacterAttribute attackAttribute;
  WeaponItem starterWeapon;

  public WeaponItem getStarterWeapon() {
    return starterWeapon;
  }

  public CharacterAttribute getAttackAttribute() {
    return attackAttribute;
  }

  public EnumMap<CharacterAttribute, Integer> getLevelingAttributes() {
    return levelingAttributes;
  }

  public EnumSet<WeaponType> getValidWeaponTypes() {
    return validWeaponTypes;
  }

  public EnumSet<ArmorType> getValidArmorTypes() {
    return validArmorTypes;
  }

}
