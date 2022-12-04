package Hero;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;

import ENUMS.*;
import Interfaces.*;

public class Hero implements GameCharacter, InventoryManager, EquipmentManager {
  private int level = 1;
  private String name;
  private HeroTypes heroType;
  private EnumMap<HeroAttributes, Integer> heroAttributes;
  private EnumSet<ArmorTypes> validArmorTypes;
  private EnumSet<WeaponTypes> validWeaponTypes;
  private ArrayList<Item> inventory = new ArrayList<Item>(15);
  private EnumMap<EquipmentSlots, Equipment> equippedItems = new EnumMap<EquipmentSlots, Equipment>(
      EquipmentSlots.class);

  public void loot(Item item) {
    boolean inventorised = InventoryManager.insert(inventory, item);
    System.out.println(inventorised);
  }

  public String getName() {
    return name;
  }

  public int getLevel() {
    return level;
  }

  public EnumSet<WeaponTypes> getValidWeaponTypes() {
    return validWeaponTypes;
  }

  public EnumSet<ArmorTypes> getValidArmorTypes() {
    return validArmorTypes;
  }

  public EnumMap<HeroAttributes, Integer> getHeroAttributes() {
    return heroAttributes;
  }

  private Hero(HeroBuilder builder) {
    this.name = builder.name;
    this.heroType = builder.heroType;

    this.heroAttributes = heroType.getStartingAttributes();
    this.validWeaponTypes = WeaponTypes.getValidTypes(builder.heroType);
    this.validArmorTypes = ArmorTypes.getValidTypes(builder.heroType);
  }

  public static class HeroBuilder {
    private String name;
    private HeroTypes heroType;

    public HeroBuilder(String name, HeroTypes heroType) {
      this.name = name;
      this.heroType = heroType;
    }

    public Hero build() {
      return new Hero(this);
    }
  }

}
