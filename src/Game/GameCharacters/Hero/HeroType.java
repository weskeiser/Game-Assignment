package Game.GameCharacters.Hero;

import java.util.EnumMap;
import java.util.EnumSet;

import Game.GameCharacters.CharacterAttribute;
import Game.GameCharacters.CharacterType;
import Game.Items.Equipment.Armor.ArmorType;
import Game.Items.Equipment.Weapon.WeaponType;
import Game.Items.Equipment.Weapon.Weapons;

public enum HeroType implements CharacterType, HeroInitiator {
  WARRIOR() {
    public EnumMap<CharacterAttribute, Integer> init() {
      this.levelAttributes = this.createAttributeMap(3, 2, 1);
      this.validWeaponTypes = EnumSet.of(WeaponType.AXE, WeaponType.HAMMER, WeaponType.SWORD);
      this.validArmorTypes = EnumSet.of(ArmorType.PLATE, ArmorType.MAIL);
      this.damagingAttribute = CharacterAttribute.STRENGTH;
      this.starterWeapon = Weapons.WOODEN_SWORD;
      return createAttributeMap(5, 2, 1);
    }
  },
  ROGUE {
    public EnumMap<CharacterAttribute, Integer> init() {
      this.levelAttributes = this.createAttributeMap(1, 4, 1);
      this.validWeaponTypes = EnumSet.of(WeaponType.DAGGER, WeaponType.SWORD);
      this.validArmorTypes = EnumSet.of(ArmorType.LEATHER, ArmorType.MAIL);
      this.damagingAttribute = CharacterAttribute.DEXTERITY;
      this.starterWeapon = Weapons.WOODEN_SWORD;
      return createAttributeMap(2, 6, 1);
    }
  },
  RANGER {
    public EnumMap<CharacterAttribute, Integer> init() {
      this.levelAttributes = this.createAttributeMap(1, 5, 1);
      this.validWeaponTypes = EnumSet.of(WeaponType.BOW);
      this.validArmorTypes = EnumSet.of(ArmorType.LEATHER, ArmorType.MAIL);
      this.damagingAttribute = CharacterAttribute.DEXTERITY;
      this.starterWeapon = Weapons.MAKESHIFT_BOW;
      return createAttributeMap(1, 7, 1);
    }
  },
  MAGE {
    public EnumMap<CharacterAttribute, Integer> init() {
      this.levelAttributes = this.createAttributeMap(1, 1, 5);
      this.validWeaponTypes = EnumSet.of(WeaponType.STAFF, WeaponType.WAND);
      this.validArmorTypes = EnumSet.of(ArmorType.CLOTH);
      this.damagingAttribute = CharacterAttribute.INTELLIGENCE;
      this.starterWeapon = Weapons.CRACKED_WAND;
      return createAttributeMap(1, 1, 8);

    }
  };

  EnumMap<CharacterAttribute, Integer> levelAttributes;
  EnumSet<WeaponType> validWeaponTypes;
  EnumSet<ArmorType> validArmorTypes;
  CharacterAttribute damagingAttribute;
  Weapons starterWeapon;

  public Weapons getStarterWeapon() {
    return starterWeapon;
  }

  public CharacterAttribute getDamagingAttribute() {
    return damagingAttribute;
  }

  public EnumMap<CharacterAttribute, Integer> getLevelAttributes() {
    return levelAttributes;
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
