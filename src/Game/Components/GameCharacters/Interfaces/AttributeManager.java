package Game.Components.GameCharacters.Interfaces;

import java.util.EnumMap;
import java.util.Map;

import Game.Components.Items.Equipment.EquipmentSlot;
import Game.Components.Items.Equipment.Equippable;
import Game.Components.Items.Equipment.Armor.Armor;

public interface AttributeManager {
  EnumMap<CharacterAttribute, Integer> getCharacterAttributes();

  int levelUp();

  void gainExperience(double expGain);

  EnumMap<CharacterAttribute, Integer> getDefensiveAttributes();

  // TODO: Change to more specific name
  //
  public static EnumMap<CharacterAttribute, Integer> newAttributeMap(int strength,
      int dexterity, int intelligence) {
    var attributeMap = new EnumMap<CharacterAttribute, Integer>(CharacterAttribute.class);
    attributeMap.put(CharacterAttribute.STRENGTH, strength);
    attributeMap.put(CharacterAttribute.DEXTERITY, dexterity);
    attributeMap.put(CharacterAttribute.INTELLIGENCE, intelligence);
    return attributeMap;
  }

  default EnumMap<CharacterAttribute, Integer> getDefensiveAttributes(
      EnumMap<EquipmentSlot, Equippable> equippedItems) {

    EnumMap<CharacterAttribute, Integer> defensiveAttributes = new EnumMap<>(CharacterAttribute.class);

    // For each equipped item
    equippedItems.forEach((equipmentSlot, charAttrVal) -> {

      // If not weapon
      if (equipmentSlot == EquipmentSlot.WEAPON)
        return;

      // Get armor attributes
      var armorAttributes = ((Armor) charAttrVal).getArmorAttributes();

      // Add each attribute to total (defensiveAttributes)
      armorAttributes.forEach((heroAttrKey, armorAttrVal) -> defensiveAttributes.put(
          heroAttrKey,
          defensiveAttributes.getOrDefault(heroAttrKey, 0) + armorAttrVal));
    });

    if (defensiveAttributes.isEmpty()) {
      return new EnumMap<>(Map.ofEntries(
          Map.entry(CharacterAttribute.STRENGTH, 0),
          Map.entry(CharacterAttribute.DEXTERITY, 0),
          Map.entry(CharacterAttribute.INTELLIGENCE, 0)));
    }

    return defensiveAttributes;
  }
};