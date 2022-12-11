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
  private List<Lootable> remains;

  private double health = 100;
  private int experienceToLevel = 30;
  private int experience = 0;
  private int level = 1;
  private EnumMap<CharacterAttribute, Integer> heroAttributes;

  private List<Item> inventory = new ArrayList<Item>(15);
  private EnumMap<EquipmentSlot, Equipment> equippedItems = new EnumMap<>(EquipmentSlot.class);

  public Item getInventoryItemByIdx(int index) throws InventoryException {
    try {
      Item fetched = (Item) inventory.toArray()[index];
      if (fetched == null)
        throw new InventoryException(InventoryErrMessages.NOT_FOUND);
      return fetched;
    } catch (InventoryException err) {
      throw (err);
    }
  }

  @Override
  public Item getFromInventory(int index) throws InventoryException {
    try {
      return getFromInventory(inventory, index);
    } catch (InventoryException err) {
      throw err;
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
    health = 0;
    defeatedBy = defeator;

    Iterator<Equipment> equpmentIterator = equippedItems.values().iterator();
    while (equpmentIterator.hasNext()) {
      remains.add((Lootable) equpmentIterator.next());
    }
    equippedItems.clear();

    inventory.forEach((item) -> {
      remains.add((Lootable) item);
    });
    inventory.clear();
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
    EnumMap<CharacterAttribute, Integer> levelAttributes = heroType.getLevelAttributes();

    int strength = levelAttributes.get(CharacterAttribute.STRENGTH);
    int dexterity = levelAttributes.get(CharacterAttribute.DEXTERITY);
    int intelligence = levelAttributes.get(CharacterAttribute.INTELLIGENCE);

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
  public Lootable takeItem(Lootable lootItem) throws LootException {
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
  public void dropItem(Item item) throws InventoryException {
    dropItem(inventory, item);
  }

  @Override
  public void unEquip(EquipmentSlot equipmentSlot) throws InvalidEquipmentException, InventoryException {
    unEquip(equipmentSlot, equippedItems, inventory);
  };

  @Override
  public void equip(int inventoryIndex) throws InvalidEquipmentException, InventoryException {
    Equipment itemFromInventory = (Equipment) getFromInventory(inventoryIndex);
    equip(itemFromInventory, inventory, equippedItems, level, heroType);
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
    showHeroAttributes(heroAttributes);
  }

  @Override
  public void showInventory() {
    showInventory(inventory);
  }

  @Override
  public void showEquippedItems() {
    showEquippedItems(equippedItems);
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

  @Override
  public void display() {
    StringBuilder displayBuilder = new StringBuilder();
    displayBuilder
        .append("\n" + blueC + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
        .append("\n" + blueC + "||||||||||||||||||||||||||||||||||||||||||||||")
        .append("\n" + blueC + "/~/~/" + "   >> "
            + resetC + name
            + blueC + " <<   |  "
            + resetC + heroType.toString()
            + blueC + " lvl."
            + yellowC + level
            + blueC + "  \\~\\~\\\n");
    System.out.println(displayBuilder.toString());

    showHeroAttributes();
    showInventory();
    showEquippedItems();

    System.out.println(blueC + "||||||||||||||||||||||||||||||||||||||||||||||");
    System.out.println(blueC + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
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
