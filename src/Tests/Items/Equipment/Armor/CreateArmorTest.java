package Tests.Items.Equipment.Armor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.EnumMap;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

import Game.Components.GameCharacters.Interfaces.CharacterAttribute;
import Game.Components.Items.Equipment.EquipmentSlot;
import Game.Components.Items.Equipment.Armor.*;
import Game.Components.Items.Equipment.Armor.ArmorItems.Mail;

public class CreateArmorTest {
  // Expected constants
  final ArmorItem ROYAL_MAIL = Mail.ROYAL_MAIL;
  final ArmorType ARMOR_TYPE = ArmorType.MAIL;
  final EquipmentSlot EQUIPMENT_SLOT = EquipmentSlot.TORSO;
  final String ARMOR_NAME = "Royal mail";
  final int REQUIRED_LEVEL = 22;

  // Test armor
  Armor testArmor = new Armor.ArmorBuilder(ROYAL_MAIL).build();

  @After
  public void resetArmor() {
    testArmor = new Armor.ArmorBuilder(ROYAL_MAIL).build();
  }

  //

  @Test
  public void WhenArmorCreated_NameIsCorrect() {
    assertEquals(ARMOR_NAME, testArmor.getName());

    assertNotEquals("saldkjf98sadf239", testArmor.getName());
  }

  @Test
  public void WhenArmorCreated_RequiredLevelIsCorrect() {
    assertEquals(REQUIRED_LEVEL, testArmor.getLevelRequirement());
  }

  @Test
  public void WhenArmorCreated_EquipmentSlotIsCorrect() {
    assertEquals(EQUIPMENT_SLOT, testArmor.getEquipmentSlot());
  }

  @Test
  public void WhenArmorCreated_ArmorTypeIsCorrect() {
    assertEquals(ARMOR_TYPE, testArmor.getEquipmentType());
  }

  @Test
  public void WhenArmorCreated_ArmorAttributesCorrect() {
    assertEquals(createAttributeMap(2, 3, 1), testArmor.getArmorAttributes());
  }

  // Helpers

  public EnumMap<CharacterAttribute, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    return new EnumMap<>(Map.ofEntries(
        Map.entry(CharacterAttribute.STRENGTH, strength),
        Map.entry(CharacterAttribute.DEXTERITY, dexterity),
        Map.entry(CharacterAttribute.INTELLIGENCE, intelligence)));
  }

}
