package Hero;

import java.util.EnumMap;
import java.util.EnumSet;

import ENUMS.*;
import Equipment.EquippedItems;
import Interfaces.IEquipment;
import Interfaces.IEquipmentManager;
import Interfaces.IHero;

public class Hero implements IHero, IEquipmentManager {
  private int level = 1;
  private String name;
  private HeroTypes heroType;
  private EnumMap<LevelAttributes, Integer> levelAttributes;
  private EnumSet<ArmorTypes> validArmorTypes;
  private EnumSet<WeaponTypes> validWeaponTypes;

  private EquippedItems equipped = new EquippedItems();

  public boolean equip(IEquipment item) {
    return true;
  };

  public boolean unEquip(IEquipment item) {
    return true;
  };

  public boolean displayItems() {
    return true;
  }

  public boolean displayArmorAttributes() {
    return true;
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

  public EnumMap<LevelAttributes, Integer> getLevelAttributes() {
    return levelAttributes;
  }

  private Hero(HeroBuilder builder) {
    this.name = builder.name;
    this.heroType = builder.heroType;

    this.levelAttributes = heroType.getLevelAttributes();
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
