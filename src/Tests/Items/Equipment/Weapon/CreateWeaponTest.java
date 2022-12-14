package Tests.Items.Equipment.Weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Test;

import Components.Items.Equipment.EquipmentSlot;
import Components.Items.Equipment.Weapon.*;

public class CreateWeaponTest {
  // String weaponName = "Crooked bow";
  // Weapons weapon = Weapons.CROOKED_BOW;
  // WeaponType weaponType = WeaponType.BOW;
  // int requiredLevel = 3;
  // double damageMultiplier = 1.3;

  // Expected constants
  String weaponName = "Cleaning staff";
  WeaponItem weapon = WeaponItem.CLEANING_STAFF;
  WeaponType weaponType = WeaponType.STAFF;
  int requiredLevel = 17;
  double damageMultiplier = 4.2;

  // Test weapon
  Weapon testWeapon = new Weapon.WeaponBuilder(weapon).build();

  @After
  public void resetWeapon() {
    testWeapon = new Weapon.WeaponBuilder(weapon).build();
  }

  //

  @Test
  public void whenWeaponCreated_nameIsCorrect() {
    assertEquals(weaponName, testWeapon.getName());

    assertNotEquals("98sad7klsjodf", testWeapon.getName());
  }

  @Test
  public void whenWeaponCreated_requiredLevelIsCorrect() {
    assertEquals(requiredLevel, testWeapon.getLevelRequirement());

    assertNotEquals(1, testWeapon.getLevelRequirement());
    assertNotEquals(18, testWeapon.getLevelRequirement());
  }

  @Test
  public void whenWeaponCreated_equipmentSlotIsCorrect() {
    assertEquals(EquipmentSlot.WEAPON, testWeapon.getEquipmentSlot());

    assertNotEquals(EquipmentSlot.LEGS, testWeapon.getEquipmentSlot());
  }

  @Test
  public void whenWeaponCreated_weaponTypeIsCorrect() {
    assertEquals(weaponType, testWeapon.getEquipmentType());

    assertNotEquals(WeaponType.HAMMER, testWeapon.getEquipmentType());
  }

  @Test
  public void whenWeaponIsCreated_damageMultiplierIsCorrect() {
    assertEquals(damageMultiplier, testWeapon.getDamageMultiplier(), 0);

    assertNotEquals(4.1, testWeapon.getDamageMultiplier(), 0);
    assertNotEquals(4.3, testWeapon.getDamageMultiplier(), 0);
  }

}
