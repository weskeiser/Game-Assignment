package Game.Items.Equipment.Armor;

import Game.Items.Lootable;
import Game.Items.Equipment.Equipment;
import Game.Items.Equipment.EquipmentSlot;

public class Armor implements Equipment, Lootable {
  private ArmorItems armor;
  private EquipmentSlot equipmentSlot;

  @Override
  public int getLevelRequirement() {
    return armor.getLevelRequirement();
  }

  @Override
  public ArmorType getEquipmentType() {
    return armor.getArmorType();
  }

  @Override
  public double getDamageMultiplier() {
    return armor.getDamageMultiplier();
  }

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

  private Armor(ArmorBuilder builder) {
    this.armor = builder.armor;
    this.equipmentSlot = armor.getEquipmentSlot();
  }

  public static class ArmorBuilder {
    private ArmorItems armor;

    public ArmorBuilder(ArmorItems armor) {
      this.armor = armor;
    }

    public Armor build() {
      return new Armor(this);
    }
  }

}
