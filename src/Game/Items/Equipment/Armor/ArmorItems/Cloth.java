package Game.Items.Equipment.Armor.ArmorItems;

import java.util.EnumMap;

import Game.GameCharacters.Interfaces.CharacterAttribute;
import Game.Items.Equipment.EquipmentSlot;
import Game.Items.Equipment.Armor.ArmorItem;
import Game.Items.Equipment.Armor.ArmorType;

public enum Cloth implements ArmorItem {
  IRONED_ROBE_TOP() {
    public void init() {
      name = "Ironed robe top";
      description = "Straight off your mothers ironing table.";
      levelRequirement = 1;
      armorType = ArmorType.CLOTH;
      equipmentSlot = EquipmentSlot.TORSO;
      armorAttributes = armorType.getBaseArmorAttributes();
      applyArmorAttributeMultiplier();
    }
  },

  WRINKLY_ROBE_BOTTOMS() {
    public void init() {
      name = "Wrinkly robe bottoms";
      description = "No time for ironing.";
      levelRequirement = 11;
      armorType = ArmorType.CLOTH;
      equipmentSlot = EquipmentSlot.LEGS;
      armorAttributes = armorType.getBaseArmorAttributes();
      applyArmorAttributeMultiplier();
    }
  };

  String name;
  int levelRequirement;
  ArmorType armorType;
  String description;
  EquipmentSlot equipmentSlot;
  EnumMap<CharacterAttribute, Integer> armorAttributes;
  // TODO: Make all armorAttributes enum maps double instead of integer

  public EnumMap<CharacterAttribute, Integer> getArmorAttributes() {
    return armorAttributes;
  };

  public EquipmentSlot getEquipmentSlot() {
    return equipmentSlot;
  }

  public int getLevelRequirement() {
    return levelRequirement;
  }

  public String getName() {
    return name;
  }

  public ArmorType getArmorType() {
    return armorType;
  }

  public String getDescription() {
    return description;
  }

  // double multiplier;
  // void setMultiplier() {
  // multiplier = levelRequirement
  // }

  void applyArmorAttributeMultiplier() {
    armorAttributes.put(CharacterAttribute.STRENGTH,
        (1 + armorAttributes.get(CharacterAttribute.STRENGTH) / 100) * (levelRequirement / 10 + 1));
    armorAttributes.put(CharacterAttribute.DEXTERITY,
        (1 + armorAttributes.get(CharacterAttribute.DEXTERITY) / 100) * (levelRequirement / 10 + 1));
    armorAttributes.put(CharacterAttribute.INTELLIGENCE,
        (1 + armorAttributes.get(CharacterAttribute.INTELLIGENCE) / 100) * (levelRequirement / 10 + 1));
  }
}
