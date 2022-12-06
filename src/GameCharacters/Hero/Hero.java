package GameCharacters.Hero;

import Items.Item;

import java.util.*;

import Exceptions.*;
import Items.Equipment.*;
import Items.Equipment.Weapon.*;
import GameCharacters.GameCharacter;
import GameCharacters.Actions.CombatManager;

public class Hero implements GameCharacter, EquipmentManager, CombatManager {
  public static final String resetC = "\u001B[0m";
  public static final String redC = "\u001b[35m";
  public static final String greenC = "\u001B[32m";
  public static final String blueC = "\u001B[34m";
  public static final String yellowC = "\u001b[33m";

  private int level = 1;
  private String name;
  private HeroType heroType;
  private int health = 100;
  private EnumMap<HeroAttribute, Integer> heroAttributes;
  private List<Item> inventory = new ArrayList<Item>(15);
  private EnumMap<EquipmentSlot, Equipment> equippedItems = new EnumMap<EquipmentSlot, Equipment>(
      EquipmentSlot.class);

  private Weapon getEquippedWeapon() throws InvalidWeaponException {
    try {
      Weapon equippedWeapon = (Weapon) equippedItems.get(EquipmentSlot.WEAPON);

      if (!(equippedWeapon instanceof Weapon)) {
        throw new InvalidWeaponException(InvalidWeaponException.Messages.NO_WEAPON);
      }

      return equippedWeapon;
    } catch (InvalidWeaponException err) {
      throw err;
    }
  }

  private double calculateMaxHit() {
    Weapon equippedWeapon = null;

    try {
      Weapon fetched = getEquippedWeapon();
      equippedWeapon = fetched;
    } catch (Throwable err) {
      System.out.println(err.getMessage());
    }

    HeroAttribute damagingAttribute = heroType.getDamagingAttribute();
    int attributeDamage = heroAttributes.get(damagingAttribute);

    double weaponDamage = (equippedWeapon != null) ? equippedWeapon.getDamageMultiplier() : 1;

    System.out.println("Calculated damage: " + weaponDamage * (1 + attributeDamage / 100));
    return weaponDamage * (1 + attributeDamage / 100);
  }

  @Override
  public void attack(GameCharacter foe) {
    double maxHit = calculateMaxHit();

    foe.defendYourself(maxHit);
  }

  private void takeDamage(double maxHit) {

  };

  @Override
  public void defendYourself(double maxHit) {
    System.out.println("Owwwww!!");
    takeDamage(maxHit);
  };

  @Override
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
  public void loot(Item item) {
    addToInventory(item);
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
      this.unEquip(equipmentSlot, equippedItems, inventory);
    } catch (Throwable err) {
      System.out.println(err.getMessage());
    }
  };

  @Override
  public void equip(Equipment equipment) {
    try {
      this.equip(equipment, inventory, equippedItems, level, heroType);
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
