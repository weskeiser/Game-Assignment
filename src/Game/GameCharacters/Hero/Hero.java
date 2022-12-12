package Game.GameCharacters.Hero;

import java.util.*;

import Game.Exceptions.*;
import Game.Exceptions.InvalidEquipmentException.EquipmentErrMessages;
import Game.Exceptions.InventoryException.InventoryErrMessages;
import Game.GameCharacters.*;
import Game.Items.*;
import Game.Items.Equipment.*;
import Game.Items.Equipment.Weapon.Weapon;

public class Hero implements HeroCharacter, InventoryManager, EquipmentManager, CombatManager, Remains {

  private String name;
  private HeroType heroType;

  private GameCharacter defeatedBy = null;
  private List<LootableItem> remains = new ArrayList<>();

  private double health = 100;
  private int experienceToLevel = 30;
  private int experience = 0;
  private int level = 1;
  private EnumMap<CharacterAttribute, Integer> heroAttributes;

  private List<Item> inventory = new ArrayList<Item>(15);
  private EnumMap<EquipmentSlot, Equippable> equippedItems = new EnumMap<>(EquipmentSlot.class);

  @Override
  public Item findInventoryItem(int index) throws InventoryException {
    try {
      return inventory.get(index);
    } catch (IndexOutOfBoundsException err) {
      throw new InventoryException(InventoryErrMessages.NOT_FOUND);
    }
  }

  public EnumMap<CharacterAttribute, Integer> getTotalAttributes() {
    return getTotalAttributes(heroType, equippedItems);
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
      experienceToLevel = (int) (experienceToLevel * 1.5);
    }
  }

  @Override
  public void finalBlow(GameCharacter defeator) {
    finalBlow(defeator, health, defeatedBy,
        equippedItems, remains, inventory);
  }

  @Override
  public void showLoot(GameCharacter investigator) {
    if (investigator != defeatedBy) {
      System.out.println(LootException.Messages.NOT_YOURS);
      return;
    }

    System.out.println(remains);
  }

  @Override
  public GameCharacter getDefeator() {
    return defeatedBy;
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
  public void defend(double maxHit, GameCharacter foe) {
    if (health < maxHit) {
      finalBlow(foe);
      return;
    }

    takeDamage(maxHit);
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
  public LootableItem takeItem(LootableItem lootItem) throws LootException {
    boolean taken = remains.remove(lootItem);
    if (!taken)
      throw new LootException(LootException.Messages.NOT_FOUND);
    return lootItem;
  }

  @Override
  public void addToInventory(Item item) throws InventoryException {
    addToInventory(inventory, item);
  }

  public int getFreeInventorySlots() {
    return 15 - inventory.size();
  }

  @Override
  public void unEquip(EquipmentSlot equipmentSlot) throws InvalidEquipmentException, InventoryException {
    unEquip(equipmentSlot, equippedItems, inventory);
  };

  @Override
  public void equip(Equippable equipment) throws InvalidEquipmentException,
      InventoryException {
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
    System.out.println(experience);
  }

  @Override
  public void display() {
    StringBuilder displayBuilder = new StringBuilder();
    displayBuilder
        .append("\n" + redC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        // .append("\n" + redC + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
        // .append("\n" + redC + "||||||||||||||||||||||||||||||||||||||||||||||")
        .append("\n" + redC + "/~/~/" + "   >> "
            + resetC + name
            + redC + " <<   |  "
            + resetC + "lvl."
            + yellowC + level + " "
            + resetC + heroType.toString());
    System.out.println(displayBuilder.toString());

    showHeroAttributes();
    showInventory();
    System.out.println();
    showEquippedItems();

    // System.out.println(blueC + "||||||||||||||||||||||||||||||||||||||||||||||");
    // System.out.println(blueC + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
  }

  private Hero(HeroBuilder builder) {
    name = builder.name;
    heroType = builder.heroType;

    heroAttributes = heroType.init();

    // Armor royalMail = new Armor.ArmorBuilder(Mail.ROYAL_MAIL).build();

    try {
      addToInventory(new Weapon.WeaponBuilder(heroType.starterWeapon).build());
      // addToInventory(royalMail);
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
