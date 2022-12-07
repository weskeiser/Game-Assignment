package Game.Items.Equipment.Armor;

import Game.Items.Equipment.EquipmentSlot;

public enum ArmorItems {
  ROYAL_MAIL(1, "Royal mail.", ArmorType.MAIL, "This piece of armor arrived by post.", EquipmentSlot.TORSO),;

  private int levelRequirement;
  private String name;
  private ArmorType armorType;
  private String description;
  private EquipmentSlot equipmentSlot;

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

  public double getDamageMultiplier() {
    if (levelRequirement < 5) {
      return levelRequirement / 10 + 1;

    } else if (levelRequirement < 10) {
      return levelRequirement / 10 + 1.5;

    } else if (levelRequirement < 15) {
      return levelRequirement / 10 + 2;

    } else if (levelRequirement < 20) {
      return levelRequirement / 10 + 2.5;

    } else if (levelRequirement < 25) {
      return levelRequirement / 10 + 3;

    } else if (levelRequirement < 30) {
      return levelRequirement / 10 + 3.5;
    }

    return levelRequirement / 10 + 4;
  }

  public String getDescription() {
    return description;
  }

  ArmorItems(int levelRequirement, String name, ArmorType armorType, String description, EquipmentSlot equipmentSlot) {
    this.levelRequirement = levelRequirement;
    this.name = name;
    this.armorType = armorType;
    this.description = description;
    this.equipmentSlot = equipmentSlot;
  }
}
