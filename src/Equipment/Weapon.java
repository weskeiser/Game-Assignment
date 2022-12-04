package Equipment;

import Interfaces.IWeapon;
import ENUMS.Weapons;
import ENUMS.WeaponTypes;

public class Weapon implements IWeapon {
  private Weapons weapon;

  @Override
  public String inspect() {
    return weapon.getDescription();
  }

  @Override
  public int getLevelRequirement() {
    return weapon.getLevelRequirement();
  }

  @Override
  public WeaponTypes getWeaponType() {
    return weapon.getWeaponType();
  }

  @Override
  public String displayItemName() {
    return weapon.getName();
  }

  private Weapon(WeaponBuilder builder) {
    this.weapon = builder.weapon;
  }

  public static class WeaponBuilder {
    private Weapons weapon;

    public WeaponBuilder(Weapons weapon) {
      this.weapon = weapon;
    }

    public Weapon build() {
      return new Weapon(this);
    }
  }

  // @Override
  // public String inspect() {
  // return "A powerful axe from the depths of Azure";
  // }

}
