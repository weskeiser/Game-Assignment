package Equipment.Weapons;

import ENUMS.Weapon;
import ENUMS.WeaponType;
import Interfaces.IWeapon;

public class GreatAxe implements IWeapon {
  Weapon weapon = Weapon.GREATAXE;
  WeaponType type = weapon.getWeaponType();
  int levelRequirement = weapon.getLevelRequirement();
}
