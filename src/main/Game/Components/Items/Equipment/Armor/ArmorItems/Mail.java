package main.Game.Components.Items.Equipment.Armor.ArmorItems;

import java.util.EnumMap;

import main.Game.Components.GameCharacters.Interfaces.CharacterAttribute;
import main.Game.Components.Items.Equipment.EquipmentSlot;
import main.Game.Components.Items.Equipment.Armor.ArmorItem;
import main.Game.Components.Items.Equipment.Armor.ArmorType;

public enum Mail implements ArmorItem {
  HOLY_CHAIN_HOOD() {
    public void init() {
      name = "Holy chain hood";
      description = "The chain has peculiarly large holes..";
      levelRequirement = 6;
      equipmentSlot = EquipmentSlot.HEAD;
    }
  },

  STEEL_CHAIN_HOOD() {
    public void init() {
      name = "Steel chain hood";
      description = "A chainhood made of steel.";
      levelRequirement = 14;
      equipmentSlot = EquipmentSlot.HEAD;
    }
  },

  ROYAL_MAIL() {
    public void init() {
      name = "Royal mail";
      description = "This piece of armor arrived by post.";
      levelRequirement = 22;
      equipmentSlot = EquipmentSlot.TORSO;
    }
  };

  Mail() {
    init();
    setArmorAttributes();
  }

  String name;
  String description;
  int levelRequirement;
  ArmorType armorType = ArmorType.MAIL;
  EquipmentSlot equipmentSlot;
  EnumMap<CharacterAttribute, Integer> armorAttributes = new EnumMap<>(CharacterAttribute.class);

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
