package Tests.Items.Equipment.Armor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.EnumMap;

import org.junit.Test;

import Game.GameCharacters.CharacterAttribute;
import Game.Items.Equipment.EquipmentSlot;
import Game.Items.Equipment.Armor.Armor;
import Game.Items.Equipment.Armor.ArmorItem;
import Game.Items.Equipment.Armor.ArmorType;
import Game.Items.Equipment.Armor.Mail.Mail;

public class NewArmor {
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

  public EnumMap<CharacterAttribute, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    EnumMap<CharacterAttribute, Integer> attributeMap = new EnumMap<CharacterAttribute, Integer>(
        CharacterAttribute.class);
    attributeMap.put(CharacterAttribute.STRENGTH, strength);
    attributeMap.put(CharacterAttribute.DEXTERITY, dexterity);
    attributeMap.put(CharacterAttribute.INTELLIGENCE, intelligence);
    return attributeMap;
  }

  @Test
  public void correctArmorName() {
    assertEquals(armorName, testArmor.getName());
    System.out.println(testArmor.getArmorAttributes());
  }

  @Test
  public void requiredLevelCorrect() {
    assertEquals(requiredLevel, testArmor.getLevelRequirement());
  }

  @Test
  public void correctEquipmentSlot() {
    assertEquals(EquipmentSlot.TORSO, testArmor.getEquipmentSlot());
  }

  @Test
  public void correctArmorType() {
    assertEquals(armorType, testArmor.getEquipmentType());
  }

  @Test
  public void correctArmorAttributes() {
    // TODO: Make all armorAttributes enum maps double instead of integer
    assertTrue(false);
  }

}
