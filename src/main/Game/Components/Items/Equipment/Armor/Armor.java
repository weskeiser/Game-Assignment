package main.Game.Components.Items.Equipment.Armor;

import java.util.EnumMap;

import main.Game.Components.GameCharacters.Interfaces.CharacterAttribute;
import main.Game.Components.Items.Equipment.EquipmentSlot;
import main.Game.Components.Items.Equipment.Equippable;

public class Armor implements Equippable {
  private ArmorItem armor;
  private EquipmentSlot equipmentSlot;

  public EnumMap<CharacterAttribute, Integer> getArmorAttributes() {
    return armor.getArmorAttributes();
  };

  @Override
  public EquipmentSlot getEquipmentSlot() {
    return equipmentSlot;
  }

  @Override
  public ArmorType getEquipmentType() {
    return armor.getArmorType();
  }

  @Override
  public int getLevelRequirement() {
    return armor.getLevelRequirement();
  }

  @Override
  public void printLevelRequirement() {
    System.out.println(getLevelRequirement());
  }

  @Override
  public String getName() {
    return armor.getName();
  }

  @Override
  public void inspect() {
    System.out.println(armor.getDescription());
  }

  @Override
  public void printName() {
    System.out.println(getName());
  }

  private Armor(ArmorBuilder builder) {
    this.armor = builder.armor;
    // armor.init();
    this.equipmentSlot = armor.getEquipmentSlot();
  }

  public static class ArmorBuilder {
    private ArmorItem armor;

    public ArmorBuilder(ArmorItem armor) {
      this.armor = armor;
    }

    public Armor build() {
      return new Armor(this);
    }
  }

}