package ENUMS;

import java.util.EnumMap;
import java.util.EnumSet;

import Equipment.Weapon.WeaponTypes;
import Interfaces.HeroType;

public enum HeroTypes implements HeroType {
  WARRIOR() {
    @Override
    public EnumMap<HeroAttributes, Integer> init() {
      this.levelAttributes = this.createAttributeMap(3, 2, 1);
      this.validWeaponTypes = EnumSet.of(WeaponTypes.AXE, WeaponTypes.HAMMER, WeaponTypes.SWORD);
      this.validArmorTypes = EnumSet.of(ArmorTypes.PLATE, ArmorTypes.MAIL);
      return createAttributeMap(5, 2, 1);
    }
  },
  ROGUE {
    @Override
    public EnumMap<HeroAttributes, Integer> init() {
      this.levelAttributes = this.createAttributeMap(1, 4, 1);
      this.validWeaponTypes = EnumSet.of(WeaponTypes.DAGGER, WeaponTypes.SWORD);
      this.validArmorTypes = EnumSet.of(ArmorTypes.LEATHER, ArmorTypes.MAIL);
      return createAttributeMap(2, 6, 1);
    }
  },
  RANGER {
    @Override
    public EnumMap<HeroAttributes, Integer> init() {
      this.levelAttributes = this.createAttributeMap(1, 5, 1);
      this.validWeaponTypes = EnumSet.of(WeaponTypes.BOW);
      this.validArmorTypes = EnumSet.of(ArmorTypes.LEATHER, ArmorTypes.MAIL);
      return createAttributeMap(1, 7, 1);
    }
  },
  MAGE {
    @Override
    public EnumMap<HeroAttributes, Integer> init() {
      this.levelAttributes = this.createAttributeMap(1, 1, 5);
      this.validWeaponTypes = EnumSet.of(WeaponTypes.STAFF, WeaponTypes.WAND);
      this.validArmorTypes = EnumSet.of(ArmorTypes.CLOTH);
      return createAttributeMap(1, 1, 8);

    }
  };

  EnumMap<HeroAttributes, Integer> levelAttributes;
  EnumSet<WeaponTypes> validWeaponTypes;
  EnumSet<ArmorTypes> validArmorTypes;

  @Override
  public EnumMap<HeroAttributes, Integer> getLevelAttributes() {
    return levelAttributes;
  }

  @Override
  public EnumSet<WeaponTypes> getValidWeaponTypes() {
    return validWeaponTypes;
  }

  @Override
  public EnumSet<ArmorTypes> getValidArmorTypes() {
    return validArmorTypes;
  }

  @Override
  public EnumMap<HeroAttributes, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    EnumMap<HeroAttributes, Integer> attributeMap = new EnumMap<>(HeroAttributes.class);
    attributeMap.put(HeroAttributes.STRENGTH, strength);
    attributeMap.put(HeroAttributes.DEXTERITY, dexterity);
    attributeMap.put(HeroAttributes.INTELLIGENCE, intelligence);
    return attributeMap;
  }
}
