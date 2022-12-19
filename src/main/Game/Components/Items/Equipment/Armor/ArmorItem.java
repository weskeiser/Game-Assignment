package main.Game.Components.Items.Equipment.Armor;

import java.util.Arrays;
import java.util.EnumMap;

import main.Game.Components.GameCharacters.Interfaces.CharacterAttribute;
import main.Game.Components.Items.Equipment.EquipmentSlot;

public interface ArmorItem {
  void init();

  public String getName();

  public String getDescription();

  public EquipmentSlot getEquipmentSlot();

  public int getLevelRequirement();

  public ArmorType getArmorType();

  public EnumMap<CharacterAttribute, Integer> getArmorAttributes();

  default void setArmorAttributes(ArmorType armorType, EnumMap<CharacterAttribute, Integer> armorAttributes,
      int levelRequirement) {

    var baseArmorAttributes = armorType.getBaseArmorAttributes();

    Arrays.stream(CharacterAttribute.values()).forEach(attribute -> {

      armorAttributes.put(attribute, (int) (baseArmorAttributes.get(attribute) * ((double) levelRequirement / 20)));
    });
  };
}
