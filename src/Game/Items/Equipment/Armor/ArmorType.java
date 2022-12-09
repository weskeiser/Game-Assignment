package Game.Items.Equipment.Armor;

import java.util.EnumMap;

import Game.GameCharacters.Hero.CharacterAttribute;

public enum ArmorType {
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
    EnumMap<CharacterAttribute, Integer> attributeMap = new EnumMap<CharacterAttribute, Integer>(
        CharacterAttribute.class);
    attributeMap.put(CharacterAttribute.STRENGTH, strength);
    attributeMap.put(CharacterAttribute.DEXTERITY, dexterity);
    attributeMap.put(CharacterAttribute.INTELLIGENCE, intelligence);
    return attributeMap;
  }
}
