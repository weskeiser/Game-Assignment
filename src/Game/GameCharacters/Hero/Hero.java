package Game.GameCharacters.Hero;

import java.util.*;

import Game.Exceptions.*;
import Game.GameCharacters.GameCharacter;
import Game.Items.*;
import Game.Items.Equipment.*;
import Game.Items.Equipment.Weapon.*;
import Game.GameCharacters.*;
import Game.GameCharacters.Actions.CombatManager;

public class Hero implements GameCharacter, EquipmentManager, CombatManager, Remains {
  public static final String resetC = "\u001B[0m";
  public static final String redC = "\u001b[35m";
  public static final String greenC = "\u001B[32m";
  public static final String blueC = "\u001B[34m";
  public static final String yellowC = "\u001b[33m";

  private GameCharacter defeatedBy = null;
  private List<Lootable> remains;

  private int level = 1;
  private double health = 100;
  private String name;
  private HeroType heroType;
  private EnumMap<HeroAttribute, Integer> heroAttributes;

  private List<Item> inventory = new ArrayList<Item>(15);
  private EnumMap<EquipmentSlot, Equipment> equippedItems = new EnumMap<EquipmentSlot, Equipment>(
      EquipmentSlot.class);

  @Override
  public Weapon getEquippedWeapon() throws InvalidWeaponException {
    try {
      Weapon equippedWeapon = (Weapon) equippedItems.get(EquipmentSlot.WEAPON);

      if (equippedWeapon == null) {
        throw new InvalidWeaponException(InvalidWeaponException.Messages.NO_WEAPON);
      }

      return equippedWeapon;
    } catch (InvalidWeaponException err) {
      throw err;
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

  private void takeDamage(double damage) {
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

  public Item getInventoryItemByIdx(int index) {
    try {
      return (Item) inventory.toArray()[index];
    } catch (Throwable err) {
      throw (err);
    }
  }

  @Override
  public int levelUp() {
    EnumMap<HeroAttribute, Integer> levelAttributes = heroType.getLevelAttributes();

    int strength = levelAttributes.get(HeroAttribute.STRENGTH);
    int dexterity = levelAttributes.get(HeroAttribute.DEXTERITY);
    int intelligence = levelAttributes.get(HeroAttribute.INTELLIGENCE);

    this.heroAttributes.replaceAll((k, v) -> {
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

    this.level++;
    System.out.println(this.level);
    return this.level;
  }

  @Override
  public Lootable takeItem(Lootable lootItem) throws LootException {
    boolean taken = remains.remove(lootItem);
    if (!taken)
      throw new LootException(LootException.Messages.NOT_FOUND);
    return lootItem;
  }

  @Override
  public void addToInventory(Item item) {
    try {
      addToInventory(inventory, item);
    } catch (InventoryException err) {
      System.out.println(err.getMessage());
    }
  }

  @Override
  public void dropItem(Item item) {
    try {
      dropItem(inventory, item);
    } catch (InventoryException err) {
      System.out.println(err.getMessage());
    }
  }

  @Override
  public void unEquip(EquipmentSlot equipmentSlot) {
    try {
      unEquip(equipmentSlot, equippedItems, inventory);
    } catch (Throwable err) {
      System.out.println(err.getMessage());
    }
  };

  @Override
  public void equip(Equipment equipment) {
    try {
      equip(equipment, inventory, equippedItems, level, heroType);
    } catch (Throwable err) {
      System.out.println(err.getMessage());
    }
  }

  @Override
  public EnumMap<HeroAttribute, Integer> getHeroAttributes() {
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
  public void display() {
    System.out.println("\n" + blueC + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    System.out.println(blueC + "||||||||||||||||||||||||||||||||||||||||||||||\n");
    System.out
        .println(
            blueC + "/~/~/" + "   >> " + resetC + name + blueC + " <<   |  " + resetC + heroType.toString()
                + blueC + " lvl." + yellowC + level + blueC + "  \\~\\~\\\n");
    showHeroAttributes();
    showInventory();
    showEquippedItems();
    System.out.println(blueC + "||||||||||||||||||||||||||||||||||||||||||||||");
    System.out.println(blueC + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
  }

  @Override
  public void showLevel() {
    System.out.println(level);
  }

  void showHeroAttributes() {
    StringBuilder keyBuilder = new StringBuilder();
    StringBuilder valueBuilder = new StringBuilder();
    keyBuilder.append(blueC + "   ~");

    heroAttributes.forEach((k, v) -> {
      keyBuilder.append(" " + greenC + k + blueC + " ~");
      valueBuilder.append(yellowC + v + "          ");
    });

    System.out.println(keyBuilder.toString() + "\n        " + valueBuilder.toString());

  }

  @Override
  public void showInventory() {
    StringBuilder builder = new StringBuilder();
    Object[] inv = inventory.toArray();

    builder.append("\n" + blueC + "~~~~~~~~~~~~" + resetC + " INVENTORY " + redC + "(" + yellowC + inv.length + "/15"
        + redC + ")" + blueC + " ~~~~~~~~~~~~\n");

    for (int i = 0; i < inv.length; i++) {
      builder.append(blueC + "~~~~~~~~~~~~ " + redC + (i + 1) + ". ");
      builder.append(yellowC + ((Item) inv[i]).getName() + "\n");
    }

    builder.append(blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    System.out.println(builder.toString());
  }

  @Override
  public void showEquippedItems() {
    StringBuilder builder = new StringBuilder();

    builder.append("\n" + blueC + "~~~~~~~~~~~~" + resetC + " EQUIPPED " + blueC + "~~~~~~~~~~~~~~~~~~~~\n");

    equippedItems.forEach((k, v) -> {
      builder.append(blueC + "~~~~~~~~~~~~ " + redC + k.toString() + ": ");
      builder.append(yellowC + v.getName() + "\n");
    });

    builder.append(blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    System.out.println(builder.toString());
  }

  private Hero(HeroBuilder builder) {
    name = builder.name;
    heroType = builder.heroType;

    heroAttributes = heroType.init();

    addToInventory(new Weapon.WeaponBuilder(heroType.starterWeapon).build());
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
