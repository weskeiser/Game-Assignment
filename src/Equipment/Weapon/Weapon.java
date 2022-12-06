package Equipment.Weapon;

import Interfaces.IWeapon;
import ENUMS.*;

public class Weapon implements IWeapon {
  private Weapons weapon;
  private EquipmentSlots equipmentSlot = EquipmentSlots.WEAPON;

  @Override
  public EquipmentSlots getEquipmentSlot() {
    return equipmentSlot;
  }

  @Override
  public String inspect() {
    return weapon.getDescription();
  }

  @Override
  public int getLevelRequirement() {
    return weapon.getLevelRequirement();
  }

  @Override
  public WeaponTypes getEquipmentType() {
    return weapon.getWeaponType();
  }

  @Override
  public String getName() {
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
