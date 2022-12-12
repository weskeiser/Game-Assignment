package Game.Items.Equipment.Armor;

import java.util.EnumMap;

import Game.GameCharacters.CharacterAttribute;
import Game.Items.Equipment.EquipmentSlot;

public interface ArmorItem {
  void init();

  public EquipmentSlot getEquipmentSlot();

  public int getLevelRequirement();

  public String getName();

  public ArmorType getArmorType();

  public EnumMap<CharacterAttribute, Integer> getArmorAttributes();

  public String getDescription();
}
