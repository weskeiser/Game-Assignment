package Hero;

import ENUMS.HeroType;
import Interfaces.IEquipmentManager;

public class Hero {
  private String name;
  private int level = 1;
  private HeroType heroType;
  private IEquipmentManager equipped;

  private String validWeaponTypes;
  private String validArmorTypes;
  private boolean levelAttributes;

  public String getName() {
    return name;
  }

  public int getLevel() {
    return level;
  }

  public String getValidWeaponTypes() {
    return validWeaponTypes;
  }

  public String getValidArmorTypes() {
    return validArmorTypes;
  }

  public boolean getLevelAttributes() {
    return levelAttributes;
  }

  private Hero(HeroBuilder builder) {
    this.name = builder.name;
  }

  public static class HeroBuilder {
    private String name;
    private boolean levelAttributes;
    // private int level = 1;
    // private String equipment;
    // private String validWeaponTypes;
    // private String validArmorTypes;

    public HeroBuilder(String name) {
      this.name = name;
      // this.level = level;
      // this.equipment = equipment;
      // this.validWeaponTypes = validWeaponTypes;
      // this.validArmorTypes = validArmorTypes;
    }

    public HeroBuilder setLevelAttributes(boolean levelAttributes) {
      this.levelAttributes = levelAttributes;
      return this;
    }

    public Hero build() {
      return new Hero(this);
    }
  }

}
