package Hero;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;

import ENUMS.*;
import Interfaces.*;

public class Hero implements GameCharacter, InventoryManager, EquipmentManager {
  private int level = 1;
  private String name;
  private HeroType heroType;
  private EnumMap<HeroAttributes, Integer> heroAttributes;
  private ArrayList<Item> inventory = new ArrayList<Item>(15);
  private EnumMap<EquipmentSlots, Equipment> equippedItems = new EnumMap<EquipmentSlots, Equipment>(
      EquipmentSlots.class);

  @Override
  public EnumMap<EquipmentSlots, Equipment> getEquippedItems() {
    return equippedItems;
  }

  @Override
  public void loot(Item item) {
    boolean inventorised = InventoryManager.insert(inventory, item);
    System.out.println(inventorised);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getLevel() {
    return level;
  }

  @Override
  public EnumMap<HeroAttributes, Integer> getHeroAttributes() {
    return heroAttributes;
  }

  @Override
  public EnumSet<WeaponTypes> getValidWeaponTypes() {
    return heroType.getValidWeaponTypes();
  }

  @Override
  public EnumSet<ArmorTypes> getValidArmorTypes() {
    return heroType.getValidArmorTypes();
  }

  private Hero(HeroBuilder builder) {
    name = builder.name;
    heroType = builder.heroType;

    heroType.init();
    heroAttributes = heroType.getStartingAttributes();
  }

  public static class HeroBuilder {
    private String name;
    private HeroType heroType;

    public HeroBuilder(String name, HeroType heroType) {
      this.name = name;
      this.heroType = heroType;
    }

    public Hero build() {
      return new Hero(this);
    }
  }

}
