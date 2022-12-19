package main.Game.Components.Items.Equipment.Armor;

import java.util.EnumMap;

import main.Game.Components.GameCharacters.Interfaces.AttributeManager;
import main.Game.Components.GameCharacters.Interfaces.CharacterAttribute;
import main.Game.Components.Items.Equipment.EquipmentType;

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
    baseArmorAttributes = AttributeManager.newAttributeMap(strength, dexterity, intelligence);
  }

}
