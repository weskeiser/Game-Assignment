package ENUMS;

public enum Weapon {
  SWORDIE(1, WeaponType.SWORD),
  GREATAXE(5, WeaponType.AXE),
  ;

  int levelRequirement;
  WeaponType weaponType;

  Weapon(int _levelRequirement, WeaponType _weaponType) {
    levelRequirement = _levelRequirement;
    weaponType = _weaponType;
  }

  public WeaponType getWeaponType() {
    return weaponType;
  }

  public int getLevelRequirement() {
    return levelRequirement;
  }

}
