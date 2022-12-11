package Tests.Items.Equipment.Armor;

import static org.junit.Assert.*;

import java.util.EnumMap;
import java.util.Map;

import org.junit.Test;

import Game.GameCharacters.CharacterAttribute;
import Game.Items.Equipment.EquipmentSlot;
import Game.Items.Equipment.Armor.*;
import Game.Items.Equipment.Armor.ArmorItems.Mail;

public class CreateArmorTest {
  ArmorType armorType = ArmorType.MAIL;
  String armorName = "Royal mail";
  ArmorItem royalMail = Mail.ROYAL_MAIL;
  int requiredLevel = 3;
  EnumMap<CharacterAttribute, Integer> baseArmorAttributes = createAttributeMap(2, 3, 1);

  // armorAttributes.put(CharacterAttribute.STRENGTH,
  // armorAttributes.get(CharacterAttribute.STRENGTH) * levelRequirement);
  // armorAttributes.put(CharacterAttribute.DEXTERITY,
  // armorAttributes.get(CharacterAttribute.DEXTERITY) * levelRequirement);
  // armorAttributes.put(CharacterAttribute.INTELLIGENCE,
  // armorAttributes.get(CharacterAttribute.INTELLIGENCE) * levelRequirement);
  // }

  Armor testArmor = new Armor.ArmorBuilder(royalMail).build();

  @Test
  public void whenArmorCreated_nameIsCorrect() {
    assertEquals(armorName, testArmor.getName());

    assertNotEquals("saldkjf98sadf239", testArmor.getName());
    System.out.println(testArmor.getArmorAttributes());
  }

  @Test
  public void whenArmorCreated_requiredLevelIsCorrect() {
    assertEquals(requiredLevel, testArmor.getLevelRequirement());

    assertNotEquals(2, testArmor.getLevelRequirement());
    assertNotEquals(4, testArmor.getLevelRequirement());
  }

  @Test
  public void whenArmorCreated_equipmentSlotIsCorrect() {
    assertEquals(EquipmentSlot.TORSO, testArmor.getEquipmentSlot());

    assertNotEquals(EquipmentSlot.LEGS, testArmor.getEquipmentSlot());
  }

  @Test
  public void whenArmorCreated_armorTypeIsCorrect() {
    assertEquals(armorType, testArmor.getEquipmentType());

    assertNotEquals(ArmorType.CLOTH, testArmor.getEquipmentType());
  }

  @Test
  public void whenArmorCreated_armorAttributesCorrect() {
    assertTrue(false);
  }

  public EnumMap<CharacterAttribute, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    return new EnumMap<>(Map.ofEntries(
        Map.entry(CharacterAttribute.STRENGTH, strength),
        Map.entry(CharacterAttribute.DEXTERITY, dexterity),
        Map.entry(CharacterAttribute.INTELLIGENCE, intelligence)));
  }

}
