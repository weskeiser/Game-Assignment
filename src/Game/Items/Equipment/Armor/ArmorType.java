package Game.Items.Equipment.Armor;

import java.util.EnumMap;
import java.util.Map;

import Game.GameCharacters.CharacterAttribute;
import Game.Items.Equipment.EquipmentType;

public enum ArmorType implements EquipmentType {
  CLOTH(1, 1, 1),
  LEATHER(1, 1, 3),
  MAIL(2, 3, 1),
  PLATE(3, 1, 1);

  EnumMap<CharacterAttribute, Integer> baseArmorAttributes;

  public EnumMap<CharacterAttribute, Integer> getBaseArmorAttributes() {
    return baseArmorAttributes;
  }

  ArmorType(int strength, int dexterity, int intelligence) {
    baseArmorAttributes = createAttributeMap(strength, dexterity, intelligence);
  }

  public EnumMap<CharacterAttribute, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    return new EnumMap<>(Map.ofEntries(
        Map.entry(CharacterAttribute.STRENGTH, strength),
        Map.entry(CharacterAttribute.DEXTERITY, dexterity),
        Map.entry(CharacterAttribute.INTELLIGENCE, intelligence)));
  }
}
