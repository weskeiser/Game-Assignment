package Game.Items.Equipment.Weapon;

import Game.Items.Lootable;
import Game.Items.Equipment.Equipment;
import Game.Items.Equipment.EquipmentSlot;

public class Weapon implements Equipment<WeaponType>, Lootable {
  private Weapons weapon;
  private EquipmentSlot equipmentSlot = EquipmentSlot.WEAPON;

  @Override
  public EquipmentSlot getEquipmentSlot() {
    return equipmentSlot;
  }

  public double getDamageMultiplier() {
    return weapon.getDamageMultiplier();
  }

  @Override
  public int getLevelRequirement() {
    return weapon.getLevelRequirement();
  }

  @Override
  public WeaponType getEquipmentType() {
    return weapon.getWeaponType();
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
  public void printLevelRequirement() {
    System.out.println(getLevelRequirement());
  }

  @Override
  public void printName() {
    System.out.println(getName());
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
