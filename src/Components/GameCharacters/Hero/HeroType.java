package Components.GameCharacters.Hero;

import java.util.EnumMap;
import java.util.EnumSet;

import Components.GameCharacters.Interfaces.CharacterAttribute;
import Components.GameCharacters.Interfaces.CharacterType;
import Components.Items.Equipment.Armor.ArmorType;
import Components.Items.Equipment.Weapon.WeaponItem;
import Components.Items.Equipment.Weapon.WeaponType;

public enum HeroType implements CharacterType, HeroInitiator {
  WARRIOR() {
    public EnumMap<CharacterAttribute, Integer> init() {
      levelingAttributes = createAttributeMap(3, 2, 1);
      validWeaponTypes = EnumSet.of(WeaponType.AXE, WeaponType.HAMMER, WeaponType.SWORD);
      validArmorTypes = EnumSet.of(ArmorType.PLATE, ArmorType.MAIL);
      damagingAttribute = CharacterAttribute.STRENGTH;
      starterWeapon = WeaponItem.WOODEN_SWORD;
      return createAttributeMap(5, 2, 1);
    }
  },
  ROGUE {
    public EnumMap<CharacterAttribute, Integer> init() {
      levelingAttributes = createAttributeMap(1, 4, 1);
      validWeaponTypes = EnumSet.of(WeaponType.DAGGER, WeaponType.SWORD);
      validArmorTypes = EnumSet.of(ArmorType.LEATHER, ArmorType.MAIL);
      damagingAttribute = CharacterAttribute.DEXTERITY;
      starterWeapon = WeaponItem.WOODEN_SWORD;
      return createAttributeMap(2, 6, 1);
    }
  },
  RANGER {
    public EnumMap<CharacterAttribute, Integer> init() {
      levelingAttributes = createAttributeMap(1, 5, 1);
      validWeaponTypes = EnumSet.of(WeaponType.BOW);
      validArmorTypes = EnumSet.of(ArmorType.LEATHER, ArmorType.MAIL);
      damagingAttribute = CharacterAttribute.DEXTERITY;
      starterWeapon = WeaponItem.MAKESHIFT_BOW;
      return createAttributeMap(1, 7, 1);
    }
  },
  MAGE {
    public EnumMap<CharacterAttribute, Integer> init() {
      levelingAttributes = createAttributeMap(1, 1, 5);
      validWeaponTypes = EnumSet.of(WeaponType.STAFF, WeaponType.WAND);
      validArmorTypes = EnumSet.of(ArmorType.CLOTH);
      damagingAttribute = CharacterAttribute.INTELLIGENCE;
      starterWeapon = WeaponItem.CRACKED_WAND;
      return createAttributeMap(1, 1, 8);

    }
  };

  EnumMap<CharacterAttribute, Integer> levelingAttributes;
  EnumSet<WeaponType> validWeaponTypes;
  EnumSet<ArmorType> validArmorTypes;
  CharacterAttribute damagingAttribute;
  WeaponItem starterWeapon;

  public WeaponItem getStarterWeapon() {
    return starterWeapon;
  }

  public CharacterAttribute getDamagingAttribute() {
    return damagingAttribute;
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

  public EnumMap<CharacterAttribute, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    EnumMap<CharacterAttribute, Integer> attributeMap = new EnumMap<>(CharacterAttribute.class);
    attributeMap.put(CharacterAttribute.STRENGTH, strength);
    attributeMap.put(CharacterAttribute.DEXTERITY, dexterity);
    attributeMap.put(CharacterAttribute.INTELLIGENCE, intelligence);
    return attributeMap;
  }
}
