package Game.GameCharacters.Hero;

import java.util.EnumMap;
import java.util.EnumSet;

import Game.GameCharacters.CharacterType;
import Game.Items.Equipment.Armor.ArmorType;
import Game.Items.Equipment.Weapon.WeaponType;
import Game.Items.Equipment.Weapon.Weapons;

public enum HeroType implements CharacterType, HeroInitiator {
  WARRIOR() {
    public EnumMap<HeroAttribute, Integer> init() {
      this.levelAttributes = this.createAttributeMap(3, 2, 1);
      this.validWeaponTypes = EnumSet.of(WeaponType.AXE, WeaponType.HAMMER, WeaponType.SWORD);
      this.validArmorTypes = EnumSet.of(ArmorType.PLATE, ArmorType.MAIL);
      this.damagingAttribute = HeroAttribute.STRENGTH;
      this.starterWeapon = Weapons.WOODEN_SWORD;
      return createAttributeMap(5, 2, 1);
    }
  },
  ROGUE {
    public EnumMap<HeroAttribute, Integer> init() {
      this.levelAttributes = this.createAttributeMap(1, 4, 1);
      this.validWeaponTypes = EnumSet.of(WeaponType.DAGGER, WeaponType.SWORD);
      this.validArmorTypes = EnumSet.of(ArmorType.LEATHER, ArmorType.MAIL);
      this.damagingAttribute = HeroAttribute.DEXTERITY;
      this.starterWeapon = Weapons.WOODEN_SWORD;
      return createAttributeMap(2, 6, 1);
    }
  },
  RANGER {
    public EnumMap<HeroAttribute, Integer> init() {
      this.levelAttributes = this.createAttributeMap(1, 5, 1);
      this.validWeaponTypes = EnumSet.of(WeaponType.BOW);
      this.validArmorTypes = EnumSet.of(ArmorType.LEATHER, ArmorType.MAIL);
      this.damagingAttribute = HeroAttribute.DEXTERITY;
      this.starterWeapon = Weapons.MAKESHIFT_BOW;
      return createAttributeMap(1, 7, 1);
    }
  },
  MAGE {
    public EnumMap<HeroAttribute, Integer> init() {
      this.levelAttributes = this.createAttributeMap(1, 1, 5);
      this.validWeaponTypes = EnumSet.of(WeaponType.STAFF, WeaponType.WAND);
      this.validArmorTypes = EnumSet.of(ArmorType.CLOTH);
      this.damagingAttribute = HeroAttribute.INTELLIGENCE;
      this.starterWeapon = Weapons.CRACKED_WAND;
      return createAttributeMap(1, 1, 8);

    }
  };

  EnumMap<HeroAttribute, Integer> levelAttributes;
  EnumSet<WeaponType> validWeaponTypes;
  EnumSet<ArmorType> validArmorTypes;
  HeroAttribute damagingAttribute;
  Weapons starterWeapon;

  public Weapons getStarterWeapon() {
    return starterWeapon;
  }

  public HeroAttribute getDamagingAttribute() {
    return damagingAttribute;
  }

  public EnumMap<HeroAttribute, Integer> getLevelAttributes() {
    return levelAttributes;
  }

  public EnumSet<WeaponType> getValidWeaponTypes() {
    return validWeaponTypes;
  }

  public EnumSet<ArmorType> getValidArmorTypes() {
    return validArmorTypes;
  }

  public EnumMap<HeroAttribute, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    EnumMap<HeroAttribute, Integer> attributeMap = new EnumMap<>(HeroAttribute.class);
    attributeMap.put(HeroAttribute.STRENGTH, strength);
    attributeMap.put(HeroAttribute.DEXTERITY, dexterity);
    attributeMap.put(HeroAttribute.INTELLIGENCE, intelligence);
    return attributeMap;
  }
}
