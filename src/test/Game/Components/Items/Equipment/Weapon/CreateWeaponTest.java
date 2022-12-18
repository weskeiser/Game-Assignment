package test.Game.Components.Items.Equipment.Weapon;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import main.Game.Components.Items.Equipment.EquipmentSlot;
import main.Game.Components.Items.Equipment.Weapon.Weapon;
import main.Game.Components.Items.Equipment.Weapon.WeaponItem;

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
  public void CreateWeapon_GetName_NameIsCorrect() {
    assertEquals(weaponItem.getName(), testWeapon.getName());
  }

  @Test
  public void CreateWeapon_GetLevelRequirement_LevelRequirementCorrect() {
    assertEquals(weaponItem.getLevelRequirement(), testWeapon.getLevelRequirement());
  }

  @Test
  public void CreateWeapon_GetEquipmentSlot_SlotIsWEAPON() {
    assertEquals(EquipmentSlot.WEAPON, testWeapon.getEquipmentSlot());
  }

  @Test
  public void CreateWeapon_GetEquipmentType_EquipmentTypeCorrect() {
    assertEquals(weaponItem.getWeaponType(), testWeapon.getEquipmentType());
  }

  @Test
  public void CreateWeapon_GetDamageMultiplier_DamageMultiplierCorrect() {
    assertEquals(weaponItem.getDamageMultiplier(), testWeapon.getDamageMultiplier(), 0);
  }

}
