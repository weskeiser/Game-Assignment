package Tests.Items.Equipment.Weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Game.Items.Equipment.EquipmentSlot;
import Game.Items.Equipment.Weapon.Weapon;
import Game.Items.Equipment.Weapon.WeaponType;
import Game.Items.Equipment.Weapon.Weapons;

public class NewWeapon {
  // String weaponName = "Crooked bow";
  // Weapons weapon = Weapons.CROOKED_BOW;
  // WeaponType weaponType = WeaponType.BOW;
  // int requiredLevel = 3;
  // double damageMultiplier = 1.3;

  String weaponName = "Cleaning staff";
  Weapons weapon = Weapons.CLEANING_STAFF;
  WeaponType weaponType = WeaponType.STAFF;
  int requiredLevel = 17;
  double damageMultiplier = 4.2;

  Weapon testWeapon = new Weapon.WeaponBuilder(weapon).build();

  @Test
  public void correctWeaponName() {
    assertEquals(weaponName, testWeapon.getName());
  }

  @Test
  public void requiredLevelCorrect() {
    assertEquals(requiredLevel, testWeapon.getLevelRequirement());
  }

  @Test
  public void correctEquipmentSlot() {
    assertEquals(EquipmentSlot.WEAPON, testWeapon.getEquipmentSlot());
  }

  @Test
  public void correctWeaponType() {

    assertEquals(weaponType, testWeapon.getEquipmentType());
  }

  @Test
  public void correctDamageMultiplier() {
    System.out.println(testWeapon.getDamageMultiplier());
    assertEquals(damageMultiplier, testWeapon.getDamageMultiplier(), 0.01);
  }

}
