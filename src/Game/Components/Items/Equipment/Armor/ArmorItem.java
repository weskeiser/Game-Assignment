package Game.Components.Items.Equipment.Armor;

import java.util.Arrays;
import java.util.EnumMap;

import Game.Components.GameCharacters.Interfaces.CharacterAttribute;
import Game.Components.Items.Equipment.EquipmentSlot;

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

    Arrays.stream(CharacterAttribute.values()).forEach(k -> {
      armorAttributes.put(
          k,
          (int) (baseArmorAttributes.get(k) * ((double) levelRequirement / 20)));
    });
  };
}
