package Game.GameCharacters.Hero;

import java.util.*;

import Game.Exceptions.InvalidEquipmentException;
import Game.Exceptions.InvalidEquipmentException.EquipmentErrMessages;
import Game.Exceptions.InventoryException;
import Game.Exceptions.InventoryException.InventoryErrMessages;
import Game.GameCharacters.Interfaces.*;
import Game.GameCharacters.Remains.Remains;
import Game.Items.Item;
import Game.Items.LootableItem;
import Game.Items.Equipment.*;
import Game.Items.Equipment.Weapon.Weapon;
import utils.CLR;

public class Hero
    implements GameCharacter, HeroDisplayer, InventoryManager, EquipmentManager, Attacker, Defender,
    AttributeManager {

  private String name;
  private HeroType heroType;
  private double health = 100;
  private int experience = 0;
  private int experienceToLevel = 10;
  private int level = 1;
  private EnumMap<CharacterAttribute, Integer> heroAttributes;
  private List<Item> inventory = new ArrayList<Item>(15);
  private Attacker defeatedBy = null;
  private List<Remains> lootableRemains = new ArrayList<>();

  private EnumMap<EquipmentSlot, Equippable> equippedItems = new EnumMap<>(EquipmentSlot.class);

  public EnumMap<EquipmentSlot, Equippable> getEquippedItems() {
    return equippedItems;
  }

  public Remains getRemains() {

    List<LootableItem> loot = new ArrayList<>();

    equippedItems.values().forEach((item) -> loot.add((LootableItem) item));
    inventory.forEach((item) -> loot.add((LootableItem) item));

    equippedItems.clear();
    inventory.clear();

    return new Remains(loot, defeatedBy);
  }

  public List<Item> getInventory() {
    return inventory;
  }

  public void addRemains(Remains remains) {
    lootableRemains.add(remains);
  }

  @Override
  public Item findInventoryItem(int index) throws InventoryException {

    try {
      return inventory.get(index);
    } catch (IndexOutOfBoundsException err) {
      throw new InventoryException(InventoryErrMessages.NOT_FOUND);
    }
  }

  @Override
  public EnumMap<CharacterAttribute, Integer> getDefensiveAttributes() {

    return getDefensiveAttributes(equippedItems);
  }

  @Override
  public Weapon getEquippedWeapon() throws InvalidEquipmentException {

    Weapon equippedWeapon = (Weapon) equippedItems.get(EquipmentSlot.WEAPON);

    if (equippedWeapon == null) {
      throw new InvalidEquipmentException(EquipmentErrMessages.NO_WEAPON);
    }

    return equippedWeapon;
  }

  @Override
  public void gainExperience(int expGain) {

    experience += expGain;

    if (experience >= experienceToLevel) {

      levelUp();

      int difference = experience - experienceToLevel;

      experienceToLevel = (int) (experienceToLevel * 1.5) + difference;
    }
  }

  @Override
  public void receiveFinalBlow(Attacker defeator) {
    health = 0;
    defeatedBy = defeator;
  }

  @Override
  public double getHealth() {
    return health;
  }

  @Override
  public double getMaxHit() {
    return getMaxHit(this);
  }

  @Override
  public void takeDamage(double damage) {
    health = health - damage;
  };

  @Override
  public int defend(double maxHit, Attacker foe) {

    int actualHit = 1;

    if (health < maxHit) {
      receiveFinalBlow(foe);
      return actualHit;
    }

    takeDamage(actualHit);

    return actualHit;
  };

  @Override
  public CharacterType getCharacterType() {
    return heroType;
  }

  @Override
  public int levelUp() {

    EnumMap<CharacterAttribute, Integer> levelingAttributes = heroType.getLevelingAttributes();

    int strength = levelingAttributes.get(CharacterAttribute.STRENGTH);
    int dexterity = levelingAttributes.get(CharacterAttribute.DEXTERITY);
    int intelligence = levelingAttributes.get(CharacterAttribute.INTELLIGENCE);

    heroAttributes.replaceAll((k, v) -> {
      switch (k) {
        case STRENGTH:
          return v + strength;
        case DEXTERITY:
          return v + dexterity;
        case INTELLIGENCE:
          return v + intelligence;
        default:
          return v;
      }
    });

    level++;
    return level;
  }

  @Override
  public void addToInventory(Item item) throws InventoryException {
    addToInventory(inventory, item);
  }

  @Override
  public int getFreeInventorySlots() {
    return MAX_INVENTORY_SIZE - inventory.size();
  }

  @Override
  public void unEquip(EquipmentSlot equipmentSlot) throws InvalidEquipmentException, InventoryException {

    unEquip(equipmentSlot, equippedItems, inventory);
  };

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
  public EnumMap<CharacterAttribute, Integer> getHeroAttributes() {
    return heroAttributes;
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
