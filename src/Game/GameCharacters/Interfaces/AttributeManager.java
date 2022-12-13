package Game.GameCharacters.Interfaces;

import java.util.EnumMap;
import java.util.Map;

import Game.Items.Equipment.EquipmentSlot;
import Game.Items.Equipment.Equippable;
import Game.Items.Equipment.Armor.Armor;

public interface AttributeManager {
  EnumMap<CharacterAttribute, Integer> getHeroAttributes();

  int levelUp();

  void gainExperience(int expGain);

  EnumMap<CharacterAttribute, Integer> getDefensiveAttributes();

  default EnumMap<CharacterAttribute, Integer> getDefensiveAttributes(
      EnumMap<EquipmentSlot, Equippable> equippedItems) {

    EnumMap<CharacterAttribute, Integer> defensiveAttributes = new EnumMap<>(CharacterAttribute.class);

    // For each equipped item
    equippedItems.forEach((equipmentSlot, charAttrVal) -> {

      // Ignore if weapon
      if (equipmentSlot == EquipmentSlot.WEAPON)
        return;

      // Get armor attributes
      EnumMap<CharacterAttribute, Integer> armorAttributes = ((Armor) charAttrVal).getArmorAttributes();

      // Add each attribute to respective slot in defensiveAttributes
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