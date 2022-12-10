package Game.Items.Equipment.Armor;

import java.util.EnumMap;

import Game.GameCharacters.CharacterAttribute;
import Game.Items.Lootable;
import Game.Items.Equipment.Equipment;
import Game.Items.Equipment.EquipmentSlot;

public class Armor implements Equipment<ArmorType>, Lootable {
  private ArmorItem armor;
  private EquipmentSlot equipmentSlot;

  @Override
  public int getLevelRequirement() {
    return armor.getLevelRequirement();
  }

  @Override
  public ArmorType getEquipmentType() {
    return armor.getArmorType();
  }

  public EnumMap<CharacterAttribute, Integer> getArmorAttributes() {
    return armor.getArmorAttributes();
  };

  @Override
  public EquipmentSlot getEquipmentSlot() {
    return equipmentSlot;
  }

  @Override
  public void inspect() {
    System.out.println(armor.getDescription());
  }

  @Override
  public void printName() {
    System.out.println(getName());
  }

  @Override
  public String getName() {
    return armor.getName();
  }

  @Override
  public void printLevelRequirement() {
    System.out.println(getLevelRequirement());
  }

  public void init() {

  }

  private Armor(ArmorBuilder builder) {
    this.armor = builder.armor;
    armor.init();
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