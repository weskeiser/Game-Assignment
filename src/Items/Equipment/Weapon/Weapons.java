package Items.Equipment.Weapon;

public enum Weapons {
  WOODEN_SWORD(1, WeaponTypes.SWORD, "Wooden sword", "A sword fit for a novice."),
  GREATAXE(3, WeaponTypes.AXE, "GreatAxe", "A powerful axe from the depths of Azure"),
  ;

  private WeaponTypes weaponType;
  private int levelRequirement;
  private String name;
  private String description;

  public WeaponTypes getWeaponType() {
    return weaponType;
  }

  public int getLevelRequirement() {
    return levelRequirement;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  Weapons(int levelRequirement, WeaponTypes weaponType, String name, String description) {
    this.levelRequirement = levelRequirement;
    this.weaponType = weaponType;
    this.name = new String(name);
    this.description = new String(description);
  }
}