package Game.Components.GameCharacters.Hero;

import java.util.*;

import Game.Components.Exceptions.InvalidEquipmentException;
import Game.Components.Exceptions.InventoryException;
import Game.Components.Exceptions.InventoryException.InventoryErrMessages;
import Game.Components.GameCharacters.Interfaces.*;
import Game.Components.GameCharacters.Remains.Remains;
import Game.Components.Items.Item;
import Game.Components.Items.Equipment.*;
import Game.Components.Items.Equipment.Weapon.Weapon;
import utils.CLR;

public class Hero
    implements GameCharacter, HeroDisplayer, InventoryManager, EquipmentManager, Attacker, Defender,
    AttributeManager {

  public static double HERO_STARTING_HEALTH = 10;
  public static double HERO_STARTING_EXPERIENCE = 0;
  public static int HERO_STARTING_LEVEL = 1;

  private String name;
  private HeroType heroType;

  // Levels
  private int level = HERO_STARTING_LEVEL;
  private double health = HERO_STARTING_HEALTH;
  private double experience = HERO_STARTING_EXPERIENCE;

  private double experienceToLevel = 10;
  private EnumMap<CharacterAttribute, Integer> heroAttributes;

  // Combat
  private int attackCooldown = 0;
  private Defender currentlyAttacking = null;
  private Attacker defeatedBy = null;
  private List<Remains> lootableRemains = new ArrayList<>();

  // Items
  private List<Item> inventory = new ArrayList<Item>();
  private EnumMap<EquipmentSlot, Equippable> equippedItems = new EnumMap<>(EquipmentSlot.class);

  public double getExperience() {
    return experience;
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
  public double getHealth() {
    return health;
  }

  @Override
  public CharacterType getCharacterType() {
    return heroType;
  }

  @Override
  public EnumMap<CharacterAttribute, Integer> getCharacterAttributes() {
    return heroAttributes;
  }

  // Combat related: Attacks
  @Override
  public Optional<Defender> getCurrentlyAttacking() {
    return Optional.ofNullable(currentlyAttacking);
  }

  @Override
  public void setCurrentlyAttacking(Defender currentlyAttacking) {
    this.currentlyAttacking = currentlyAttacking;
  }

  @Override
  public double getMaxHit() {
    return getMaxHit(this);
  }

  @Override
  public CharacterAttribute getAttackAttribute() {
    return heroType.getAttackAttribute();
  }

  @Override
  public int getAttackSpeed() {
    int dexterityAttribute = heroAttributes.get(CharacterAttribute.DEXTERITY);

    return dexterityAttribute / 100;
  }

  @Override
  public int getAttackCooldown() {
    return attackCooldown;
  }

  @Override
  public void setAttackCooldown(int attackCooldown) {
    this.attackCooldown = attackCooldown;
  }

  @Override
  public boolean decrementIfAttackCooldown() {
    if (attackCooldown >= 1) {
      attackCooldown--;
      return true;
    }

    return false;
  }

  @Override
  public void receiveLootAccess(Remains remains) {
    lootableRemains.add(remains);
  }

  // Combat related: Defense
  @Override
  public boolean takeDamage(int damage, Attacker foe) {
    health = health - damage;

    if (health < damage) {
      receiveFinalBlow(foe);
      return false;
    }

    return true;
  };

  @Override
  public void receiveFinalBlow(Attacker defeator) {
    health = 0;
    defeatedBy = defeator;
  }

  @Override
  public boolean defend(CharacterAttribute attackAttribute) {
    int deflectionChance = getDefensiveAttributes().get(attackAttribute);

    int roll100 = new Random().nextInt(100) + 1;

    boolean deflected = false;

    if (deflectionChance >= roll100)
      deflected = true;

    return deflected;
  };

  @Override
  public Remains surrenderValuables() {

    List<Item> loot = new ArrayList<>();

    equippedItems.values().forEach(loot::add);
    inventory.forEach(loot::add);

    equippedItems.clear();
    inventory.clear();

    return new Remains(loot, defeatedBy);
  }

  // Item related
  @Override
  public Item findInventoryItem(int index) throws InventoryException {

    try {
      return inventory.get(index);
    } catch (IndexOutOfBoundsException err) {
      throw new InventoryException(InventoryErrMessages.NOT_FOUND);
    }
  }

  @Override
  public void addToInventory(Item item) throws InventoryException {
    addToInventory(inventory, item);
  }

  @Override
  public int getFreeInventorySlots() {
    return MAX_INVENTORY_SIZE - inventory.size();
  }

  // Equipment related

  @Override
  public void equip(Equippable equipment) throws InvalidEquipmentException, InventoryException {

    equip(equipment, inventory, equippedItems, level, heroType);
  }

  @Override
  public void equip(int inventoryIndex) throws InvalidEquipmentException, InventoryException {

    Equippable itemExistingInInventory = (Equippable) findInventoryItem(inventoryIndex);

    equip(itemExistingInInventory, inventory, equippedItems, level, heroType);
  }

  @Override
  public void unEquip(EquipmentSlot equipmentSlot) throws InvalidEquipmentException, InventoryException {

    unEquip(equipmentSlot, equippedItems, inventory);
  };

  @Override
  public EnumMap<EquipmentSlot, Equippable> getEquippedItems() {
    return equippedItems;
  }

  @Override
  public Optional<Weapon> getEquippedWeapon() {

    Weapon equippedWeapon = (Weapon) equippedItems.get(EquipmentSlot.WEAPON);

    // if (equippedWeapon == null) {
    // throw new InvalidEquipmentException(EquipmentErrMessages.NO_WEAPON);
    // }

    return Optional.ofNullable(equippedWeapon);

    // return equippedWeapon;
  }

  // Attributes related

  @Override
  public EnumMap<CharacterAttribute, Integer> getDefensiveAttributes() {

    return getDefensiveAttributes(equippedItems);
  }

  @Override
  public void gainExperience(double expGain) {

    experience += expGain;

    if (experience >= experienceToLevel) {

      levelUp();

      double difference = experience - experienceToLevel;

      experienceToLevel = (experienceToLevel * 1.5) + difference;
    }
  }

  @Override
  public int levelUp() {

    EnumMap<CharacterAttribute, Integer> levelingAttributes = heroType.getLevelingAttributes();

    heroAttributes.replaceAll((heroAttribute, value) -> value + levelingAttributes.get(heroAttribute));

    health = health + 2;
    level++;
    return level;
  }

  // Displaying related

  @Override
  public void showLevel() {
    System.out.println(level);
  }

  public void showCharacterAttributes(EnumMap<CharacterAttribute, Integer> characterAttributes) {
    StringBuilder keyBuilder = new StringBuilder();
    StringBuilder valueBuilder = new StringBuilder();
    keyBuilder.append(CLR.redC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    keyBuilder.append("\n" + CLR.blueC + "    ~");

    characterAttributes.forEach((k, v) -> {
      keyBuilder.append(" " + CLR.greenC + k + CLR.blueC + " ~");
      valueBuilder.append(CLR.yellowC + v + "           ");
    });

    System.out.println(keyBuilder.toString() + "\n         " + valueBuilder.toString());
  }

  @Override
  public void showHeroAttributes() {
    showCharacterAttributes(heroAttributes);
  }

  @Override
  public void showInventory() {
    showInventory(inventory);
  }

  @Override
  public void showEquippedItems() {
    showEquippedItems(equippedItems);
  }

  @Override
  public void showHealth() {
    System.out.println((int) health);
  }

  @Override
  public void display() {
    StringBuilder displayBuilder = new StringBuilder();
    displayBuilder
        .append("\n" + CLR.redC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        .append("\n" + CLR.redC + "/~/~/" + "   >> "
            + CLR.resetC + name
            + CLR.redC + " <<   |  "
            + CLR.resetC + "lvl."
            + CLR.yellowC + level + " "
            + CLR.resetC + heroType.toString());
    System.out.println(displayBuilder.toString());

    showHeroAttributes();
    showInventory();
    System.out.println();
    showEquippedItems();

  }

  private Hero(HeroBuilder builder) {
    name = builder.name;
    heroType = builder.heroType;

    heroAttributes = heroType.init();

    try {
      addToInventory(new Weapon.WeaponBuilder(heroType.starterWeapon).build());
    } catch (InventoryException err) {
      System.out.println("This should never happen: " + err.getMessage());
    }
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
