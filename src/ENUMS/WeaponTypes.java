package ENUMS;

import java.util.EnumSet;

public enum WeaponTypes {
  AXE,
  HAMMER,
  SWORD,
  DAGGER,
  BOW,
  STAFF,
  WAND;

  public static EnumSet<WeaponTypes> getValidTypes(HeroTypes heroType) {
    switch (heroType) {
      case WARRIOR:
        return (EnumSet<WeaponTypes>) EnumSet.of(AXE, HAMMER, SWORD);
      case ROGUE:
        return (EnumSet<WeaponTypes>) EnumSet.of(DAGGER, SWORD);
      case RANGER:
        return (EnumSet<WeaponTypes>) EnumSet.of(BOW);
      case MAGE:
        return (EnumSet<WeaponTypes>) EnumSet.of(STAFF, WAND);
      default:
        return EnumSet.noneOf(WeaponTypes.class);
    }
  }
}
