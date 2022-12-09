package Game.Items.Equipment.Armor.Mail;

import java.util.EnumMap;

import Game.Items.Equipment.EquipmentSlot;
import Game.Items.Equipment.Armor.ArmorItem;
import Game.Items.Equipment.Armor.ArmorType;
import Game.GameCharacters.Hero.CharacterAttribute;

public enum Mail implements ArmorItem {
  ROYAL_MAIL() {
    public void init() {
      name = "Royal mail";
      description = "This piece of armor arrived by post.";
      levelRequirement = 3;
      armorType = armorType.MAIL;
      equipmentSlot = EquipmentSlot.TORSO;
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
        armorAttributes.get(CharacterAttribute.STRENGTH) * levelRequirement);
    armorAttributes.put(CharacterAttribute.DEXTERITY,
        armorAttributes.get(CharacterAttribute.DEXTERITY) * levelRequirement);
    armorAttributes.put(CharacterAttribute.INTELLIGENCE,
        armorAttributes.get(CharacterAttribute.INTELLIGENCE) * levelRequirement);
  }

}
