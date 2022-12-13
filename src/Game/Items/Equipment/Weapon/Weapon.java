package Game.Items.Equipment.Weapon;

import Game.Items.Equipment.EquipmentSlot;
import Game.Items.Equipment.Equippable;

public class Weapon implements Equippable {
  private WeaponItem weapon;
  private EquipmentSlot equipmentSlot = EquipmentSlot.WEAPON;

  @Override
  public EquipmentSlot getEquipmentSlot() {
    return equipmentSlot;
  }

  @Override
  public WeaponType getEquipmentType() {
    return weapon.getWeaponType();
  }

  @Override
  public int getLevelRequirement() {
    return weapon.getLevelRequirement();
  }

  @Override
  public void printLevelRequirement() {
    System.out.println(getLevelRequirement());
  }

  public double getDamageMultiplier() {
    return weapon.getDamageMultiplier();
  }

  @Override
  public String getName() {
    return weapon.getName();
  }

  @Override
  public void inspect() {
    System.out.println(weapon.getDescription());
  }

  @Override
  public void printName() {
    System.out.println(getName());
  }

  private Weapon(WeaponBuilder builder) {
    this.weapon = builder.weapon;
  }

  public static class WeaponBuilder {
    private WeaponItem weapon;

    public WeaponBuilder(WeaponItem weapon) {
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
