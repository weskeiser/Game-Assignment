package GameCharacters.Hero;

import java.util.*;
import ENUMS.*;
import Interfaces.*;
import Items.Item;
import Items.Equipment.Equipment;
import Items.Equipment.EquipmentManager;
import Items.Equipment.EquipmentSlots;
import Items.Equipment.Armor.Armor;
import Items.Equipment.Armor.ArmorTypes;
import Items.Equipment.Weapon.Weapon;
import Items.Equipment.Weapon.WeaponTypes;
import Items.Inventory.InventoryManager;
import Exceptions.*;
import GameCharacters.GameCharacter;

public class Hero implements GameCharacter, InventoryManager, EquipmentManager {
  public static final String resetC = "\u001B[0m";
  public static final String redC = "\u001b[35m";
  public static final String greenC = "\u001B[32m";
  public static final String blueC = "\u001B[34m";
  public static final String yellowC = "\u001b[33m";

  private int level = 1;
  private String name;
  private HeroType heroType;
  private EnumMap<HeroAttributes, Integer> heroAttributes;
  private List<Item> inventory = new ArrayList<Item>(15);
  private EnumMap<EquipmentSlots, Equipment> equippedItems = new EnumMap<EquipmentSlots, Equipment>(
      EquipmentSlots.class);

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
    EnumMap<HeroAttributes, Integer> levelAttributes = heroType.getLevelAttributes();

    int strength = levelAttributes.get(HeroAttributes.STRENGTH);
    int dexterity = levelAttributes.get(HeroAttributes.DEXTERITY);
    int intelligence = levelAttributes.get(HeroAttributes.INTELLIGENCE);

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
  public EnumMap<HeroAttributes, Integer> getHeroAttributes() {
    return heroAttributes;
  }

  @Override
  public EnumSet<WeaponTypes> getValidWeaponTypes() {
    return heroType.getValidWeaponTypes();
  }

  @Override
  public EnumSet<ArmorTypes> getValidArmorTypes() {
    return heroType.getValidArmorTypes();
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

  private void addToInventory(List<Item> inventory, Item item) throws InventoryException {
    try {
      if (inventory.size() >= 15) {
        throw new InventoryException(InventoryExceptionMsg.INVENTORY_FULL);
      }

      if (item instanceof Weapon) {
        inventory.add((Weapon) item);
      } else if (item instanceof Armor)
        inventory.add((Armor) item);
      else {
        inventory.add(item);
      }
      System.out.println("\n" + item.getName() + " was added to the inventory.");
    } catch (InventoryException err) {
      throw err;
    }
  };

  @Override
  public void dropItem(Item item) {
    try {
      dropItem(inventory, item);
    } catch (InventoryException err) {
      System.out.println(err.getMessage());
    }
  }

  private void dropItem(List<Item> inventory, Item item) throws InventoryException {
    try {
      if (!inventory.remove(item))
        throw new InventoryException(InventoryExceptionMsg.INVENTORY_EMPTY);
      System.out.println(item.getName() + " was dropped.");
    } catch (InventoryException err) {
      throw err;
    }
  };

  private EnumMap<EquipmentSlots, Equipment> getEquippedItems() {
    return equippedItems;
  }

  @Override
  public void equip(Equipment equipment) {
    try {
      this.equip(equipment, inventory, equippedItems, level, heroType);
    } catch (Throwable err) {
      System.out.println(err.getMessage());
    }
  }

  private void equip(Equipment equipment,
      List<Item> inventory,
      EnumMap<EquipmentSlots, Equipment> equippedItems,
      int heroLevel,
      HeroType heroType)
      throws InvalidWeaponException, InventoryException {

    try {
      if (!inventory.remove(equipment)) {
        throw new InventoryException(InventoryExceptionMsg.NOT_FOUND);
      }

      switch (equipment.getEquipmentSlot()) {
        case WEAPON:
          if (equipment.getLevelRequirement() > heroLevel) {
            throw new InvalidWeaponException(InvalidWeaponExceptionMsg.LEVEL_REQUIREMENT);
          }

          if (!((EnumSet<WeaponTypes>) heroType.getValidWeaponTypes()).contains(equipment.getEquipmentType())) {
            throw new InvalidWeaponException(InvalidWeaponExceptionMsg.WRONG_TYPE);
          }

          // Replace with unequip method
          Item unwielded = equippedItems.remove(EquipmentSlots.WEAPON);
          if (unwielded instanceof Weapon) {
            addToInventory((Weapon) unwielded);
          }
          // Replace with unequip method ^^^^

          equippedItems.put(EquipmentSlots.WEAPON, equipment);
          System.out.println("wielded weapon");
          System.out.println(equippedItems);

        default:
      }
    } catch (InvalidWeaponException err) {
      throw err;
    }
  };

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
