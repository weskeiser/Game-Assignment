package ENUMS;

import java.util.EnumSet;

public enum ArmorTypes {
  CLOTH,
  LEATHER,
  MAIL,
  PLATE;

  public static EnumSet<ArmorTypes> getValidTypes(HeroTypes heroType) {
    switch (heroType) {
      case WARRIOR:
        return (EnumSet<ArmorTypes>) EnumSet.of(MAIL, PLATE);
      case ROGUE:
        return (EnumSet<ArmorTypes>) EnumSet.of(LEATHER, MAIL);
      case RANGER:
        return (EnumSet<ArmorTypes>) EnumSet.of(LEATHER, MAIL);
      case MAGE:
        return (EnumSet<ArmorTypes>) EnumSet.of(CLOTH);
      default:
        return EnumSet.noneOf(ArmorTypes.class);
    }
  }
}
