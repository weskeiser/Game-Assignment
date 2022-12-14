package Game.Components.Items.Equipment.Armor;

import java.util.EnumMap;

import Game.Components.GameCharacters.Interfaces.CharacterAttribute;
import Game.Components.Items.Equipment.EquipmentSlot;

public interface ArmorItem {
  void init();

  public EquipmentSlot getEquipmentSlot();

  public int getLevelRequirement();

  public String getName();

  public ArmorType getArmorType();

  public EnumMap<CharacterAttribute, Integer> getArmorAttributes();

  public String getDescription();
}
