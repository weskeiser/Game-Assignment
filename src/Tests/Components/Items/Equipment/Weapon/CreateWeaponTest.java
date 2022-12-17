package Tests.Components.Items.Equipment.Weapon;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import Game.Components.Items.Equipment.EquipmentSlot;
import Game.Components.Items.Equipment.Weapon.Weapon;
import Game.Components.Items.Equipment.Weapon.WeaponItem;

@RunWith(Parameterized.class)
public class CreateWeaponTest {

  Weapon testWeapon;
  WeaponItem weaponItem;

  // Params for constructor
  @Parameterized.Parameters

  public static Collection<WeaponItem> testParams() {

    return Arrays.asList(
        WeaponItem.GREATAXE,
        WeaponItem.EXCALIBUR,
        WeaponItem.BLOODY_DAGGER,
        WeaponItem.CROOKED_BOW,
        WeaponItem.CRACKED_WAND,
        WeaponItem.CLEANING_STAFF);
  }

  // Constructor
  public CreateWeaponTest(WeaponItem weaponItem) {

    this.weaponItem = weaponItem;
    this.testWeapon = new Weapon.WeaponBuilder(weaponItem).build();
  }

  //

  @Test
  public void whenWeaponCreated_nameIsCorrect() {
    assertEquals(weaponItem.getName(), testWeapon.getName());
  }

  @Test
  public void whenWeaponCreated_requiredLevelIsCorrect() {
    assertEquals(weaponItem.getLevelRequirement(), testWeapon.getLevelRequirement());
  }

  @Test
  public void whenWeaponCreated_equipmentSlotIsCorrect() {
    assertEquals(EquipmentSlot.WEAPON, testWeapon.getEquipmentSlot());
  }

  @Test
  public void whenWeaponCreated_weaponTypeIsCorrect() {
    assertEquals(weaponItem.getWeaponType(), testWeapon.getEquipmentType());
  }

  @Test
  public void whenWeaponIsCreated_damageMultiplierIsCorrect() {
    assertEquals(weaponItem.getDamageMultiplier(), testWeapon.getDamageMultiplier(), 0);
  }

}
