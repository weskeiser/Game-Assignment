package main.Game.Components.Items.Equipment.Weapon;

public enum WeaponItem {
  // Hammers

  // Axes
  GREATAXE(3, "GreatAxe", WeaponType.AXE, "A powerful axe from the depths of Azure"),

  // Swords
  WOODEN_SWORD(1, "Wooden sword", WeaponType.SWORD, "A sword fit for a novice."),
  EXCALIBUR(22, "Excalibur", WeaponType.SWORD, "Merlin have mercy."),

  // Daggers
  SHARP_SPOON(1, "Sharp spoon", WeaponType.DAGGER, "This works."),
  GUARD_RAIL(5, "Guard rail", WeaponType.DAGGER, "This was probably stolen from a castle."),
  BLOODY_DAGGER(22, "Bloody dagger", WeaponType.DAGGER, "This still has blood on it."),

  // Bows
  MAKESHIFT_BOW(1, "Makeshift bow", WeaponType.BOW, "A hurried.. work of art.. "),
  CROOKED_BOW(3, "Crooked bow", WeaponType.BOW, "A bit crooked, but it does the job."),
  TIED_BOW(22, "Tied bow", WeaponType.BOW, "It's tied at the ends."),

  // Wands
  CRACKED_WAND(1, "Cracked wand", WeaponType.WAND, "A wand that has seen better days."),
  ROOT_WAND(5, "Root wand", WeaponType.WAND, "A root with magical powers."),

  // Staffs
  CLEANING_STAFF(17, "Cleaning staff", WeaponType.STAFF, "Wipe the tears off your adversaries")

  ;

  private int levelRequirement;
  private String name;
  private WeaponType weaponType;
  private String description;
  private double weaponDamage;

  public int getLevelRequirement() {
    return levelRequirement;
  }

  public String getName() {
    return name;
  }

  public WeaponType getWeaponType() {
    return weaponType;
  }

  public String getDescription() {
    return description;
  }

  public double getDamageMultiplier() {
    if (weaponDamage != 0)
      return weaponDamage;

    if (levelRequirement < 5) {
      weaponDamage = (double) levelRequirement / 10 + 1;
      return weaponDamage;
    }

    if (levelRequirement < 10) {
      weaponDamage = (double) levelRequirement / 10 + 1.5;
      return weaponDamage;
    }

    if (levelRequirement < 15) {
      weaponDamage = (double) levelRequirement / 10 + 2;
      return weaponDamage;
    }

    if (levelRequirement < 20) {
      weaponDamage = (double) levelRequirement / 10 + 2.5;
      return weaponDamage;
    }

    if (levelRequirement < 25) {
      weaponDamage = (double) levelRequirement / 10 + 3;
      return weaponDamage;
    }

    if (levelRequirement < 30) {
      weaponDamage = (double) levelRequirement / 10 + 3.5;
      return weaponDamage;
    }

    weaponDamage = (double) levelRequirement / 10 + 4;
    return weaponDamage;
  }

  WeaponItem(int levelRequirement, String name, WeaponType weaponType, String description) {
    this.levelRequirement = levelRequirement;
    this.name = name;
    this.weaponType = weaponType;
    this.description = description;
  }
}
