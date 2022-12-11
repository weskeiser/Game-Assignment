package Game.Items.Equipment.Weapon;

public enum WeaponItem {
  WOODEN_SWORD(1, "Wooden sword", WeaponType.SWORD, "A sword fit for a novice."),
  GREATAXE(3, "GreatAxe", WeaponType.AXE, "A powerful axe from the depths of Azure"),

  MAKESHIFT_BOW(1, "Makeshift bow", WeaponType.BOW, "A hurried.. work of art.. "),
  CROOKED_BOW(3, "Crooked bow", WeaponType.BOW, "A bit crooked, but it does the job."),

  CRACKED_WAND(1, "Cracked wand", WeaponType.WAND, "A wand that has seen better days."),
  CLEANING_STAFF(17, "Cleaning staff", WeaponType.STAFF, "Floors will be wiped with your adversaries.")

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
      return (double) levelRequirement / 10 + 1;

    } else if (levelRequirement < 10) {
      return (double) levelRequirement / 10 + 1.5;

    } else if (levelRequirement < 15) {
      return (double) levelRequirement / 10 + 2;

    } else if (levelRequirement < 20) {
      return (double) levelRequirement / 10 + 2.5;

    } else if (levelRequirement < 25) {
      return (double) levelRequirement / 10 + 3;

    } else if (levelRequirement < 30) {
      return (double) levelRequirement / 10 + 3.5;
    }

    return (double) levelRequirement / 10 + 4;
  }

  public String getDescription() {
    return description;
  }

  WeaponItem(int levelRequirement, String name, WeaponType weaponType, String description) {
    this.levelRequirement = levelRequirement;
    this.name = name;
    this.weaponType = weaponType;
    this.description = description;
  }
}
