package Components.Items.Equipment.Armor.ArmorItems;

import java.util.EnumMap;
import java.util.Map;

import Components.GameCharacters.Interfaces.CharacterAttribute;
import Components.Items.Equipment.EquipmentSlot;
import Components.Items.Equipment.Armor.ArmorItem;
import Components.Items.Equipment.Armor.ArmorType;

public enum Mail implements ArmorItem {
  STEEL_CHAIN_HOOD() {
    public void init() {
      name = "Steel chain hood";
      description = "A chainhood made of steel.";
      levelRequirement = 14;
      equipmentSlot = EquipmentSlot.HEAD;
      setArmorAttributes();
    }
  },

  HOLY_CHAIN_HOOD() {
    public void init() {
      name = "Holy chain hood";
      description = "This chain has peculiarly large holes..";
      levelRequirement = 6;
      equipmentSlot = EquipmentSlot.TORSO;
      setArmorAttributes();
    }
  },

  ROYAL_MAIL() {
    public void init() {
      name = "Royal mail";
      description = "This piece of armor arrived by post.";
      levelRequirement = 22;
      equipmentSlot = EquipmentSlot.TORSO;
      setArmorAttributes();
    }
  };

  String name;
  String description;
  int levelRequirement;
  ArmorType armorType = ArmorType.MAIL;
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

  void setArmorAttributes() {
    EnumMap<CharacterAttribute, Integer> baseArmorAttributes = armorType.getBaseArmorAttributes();

    armorAttributes = new EnumMap<>(Map.ofEntries(

        Map.entry(CharacterAttribute.STRENGTH,

            (int) ((double) baseArmorAttributes.get(CharacterAttribute.STRENGTH)
                * ((double) levelRequirement / 20))),

        Map.entry(CharacterAttribute.DEXTERITY,

            (int) ((double) baseArmorAttributes.get(CharacterAttribute.DEXTERITY)
                * ((double) levelRequirement / 20))),

        Map.entry(CharacterAttribute.INTELLIGENCE,

            (int) ((double) baseArmorAttributes.get(CharacterAttribute.INTELLIGENCE)
                * ((double) levelRequirement / 20)))));
  }
}
