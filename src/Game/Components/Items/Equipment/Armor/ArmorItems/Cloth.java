package Game.Components.Items.Equipment.Armor.ArmorItems;

import java.util.EnumMap;

import Game.Components.GameCharacters.Interfaces.CharacterAttribute;
import Game.Components.Items.Equipment.EquipmentSlot;
import Game.Components.Items.Equipment.Armor.ArmorItem;
import Game.Components.Items.Equipment.Armor.ArmorType;

public enum Cloth implements ArmorItem {
  IRONED_ROBE_TOP() {
    public void init() {
      name = "Ironed robe top";
      description = "Straight off your mothers ironing board.";
      levelRequirement = 1;
      equipmentSlot = EquipmentSlot.TORSO;
    }
  },

  WRINKLY_ROBE_BOTTOMS() {
    public void init() {
      name = "Wrinkly robe bottoms";
      description = "No time for ironing.";
      levelRequirement = 11;
      equipmentSlot = EquipmentSlot.LEGS;
    }
  };

  Cloth() {
    init();
    setArmorAttributes();
  }

  String name;
  String description;
  int levelRequirement;
  ArmorType armorType = ArmorType.CLOTH;
  EquipmentSlot equipmentSlot;
  EnumMap<CharacterAttribute, Integer> armorAttributes = new EnumMap<>(CharacterAttribute.class);
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

  void setArmorAttributes() {
    setArmorAttributes(armorType, armorAttributes, levelRequirement);
  }
}
