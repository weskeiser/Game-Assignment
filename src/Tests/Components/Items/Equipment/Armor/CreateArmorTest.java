package Tests.Components.Items.Equipment.Armor;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import Game.Components.Items.Equipment.Armor.Armor;
import Game.Components.Items.Equipment.Armor.ArmorItem;
import Game.Components.Items.Equipment.Armor.ArmorItems.Cloth;
import Game.Components.Items.Equipment.Armor.ArmorItems.Mail;

@RunWith(Parameterized.class)
public class CreateArmorTest {

  Armor testArmor;
  ArmorItem armorItem;

  // Params for constructor
  @Parameterized.Parameters

  public static Collection<ArmorItem> testParams() {

    return Arrays.asList(
        Mail.HOLY_CHAIN_HOOD,
        Mail.ROYAL_MAIL,
        Cloth.WRINKLY_ROBE_BOTTOMS,
        Cloth.INVISIBLE_WIZARD_HAT);
  }

  // Constructor
  public CreateArmorTest(ArmorItem armorItem) {

    this.armorItem = armorItem;
    this.testArmor = new Armor.ArmorBuilder(armorItem).build();
  }

  //

  @Test
  public void CreatedNewArmor_NameIsCorrect() {
    assertEquals(armorItem.getName(), testArmor.getName());
  }

  @Test
  public void CreatedNewArmor_RequiredLevelIsCorrect() {
    assertEquals(armorItem.getLevelRequirement(), testArmor.getLevelRequirement());
  }

  @Test
  public void CreatedNewArmor_EquipmentSlotIsCorrect() {
    assertEquals(armorItem.getEquipmentSlot(), testArmor.getEquipmentSlot());
  }

  @Test
  public void CreatedNewArmor_ArmorTypeIsCorrect() {
    assertEquals(armorItem.getArmorType(), testArmor.getEquipmentType());
  }

  @Test
  public void CreatedNewArmor_ArmorAttributesCorrect() {
    assertEquals(armorItem.getArmorAttributes(), testArmor.getArmorAttributes());
  }

}
