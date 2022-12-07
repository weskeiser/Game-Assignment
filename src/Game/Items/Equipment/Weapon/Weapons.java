package Game.Items.Equipment.Weapon;

public enum Weapons {
  WOODEN_SWORD(1, "Wooden sword", WeaponType.SWORD, "A sword fit for a novice."),
  GREATAXE(3, "GreatAxe", WeaponType.AXE, "A powerful axe from the depths of Azure"),
  ;

  private int levelRequirement;
  private String name;
  private WeaponType weaponType;
  private String description;

  public int getLevelRequirement() {
    return levelRequirement;
  }

  public String getName() {
    return name;
  }

  public WeaponType getWeaponType() {
    return weaponType;
  }

  public double getDamageMultiplier() {
    if (levelRequirement < 5) {
      return levelRequirement / 10 + 1;

    } else if (levelRequirement < 10) {
      return levelRequirement / 10 + 1.5;

    } else if (levelRequirement < 15) {
      return levelRequirement / 10 + 2;

    } else if (levelRequirement < 20) {
      return levelRequirement / 10 + 2.5;

    } else if (levelRequirement < 25) {
      return levelRequirement / 10 + 3;

    } else if (levelRequirement < 30) {
      return levelRequirement / 10 + 3.5;
    }

    return levelRequirement / 10 + 4;
  }

  public String getDescription() {
    return description;
  }

  Weapons(int levelRequirement, String name, WeaponType weaponType, String description) {
    this.levelRequirement = levelRequirement;
    this.name = name;
    this.weaponType = weaponType;
    this.description = description;
  }
}
