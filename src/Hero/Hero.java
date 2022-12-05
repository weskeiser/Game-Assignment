package Hero;

import java.lang.constant.DirectMethodHandleDesc.Kind;
import java.util.*;
import ENUMS.*;
import Interfaces.*;
import Equipment.*;
import Exceptions.*;

public class Hero implements GameCharacter, InventoryManager, EquipmentManager {
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

  public void display() {
    System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
    System.out
        .println("/~/~/~/~/~/ " + heroType.toString() + ": " + name + " (lvl." + level + ") \\~\\~\\~\\~\\~\\\n");
    showHeroAttributes();
    showInventory();
    showEquippedItems();
    System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
  }

  void showHeroAttributes() {
    StringBuilder keyBuilder = new StringBuilder();
    StringBuilder valueBuilder = new StringBuilder();
    keyBuilder.append("   ~");

    heroAttributes.forEach((k, v) -> {
      keyBuilder.append(" " + k + " ~");
      valueBuilder.append(v + "          ");
    });

    System.out.println(keyBuilder.toString() + "\n        " + valueBuilder.toString());

  }

  @Override
  public void showInventory() {
    StringBuilder builder = new StringBuilder();
    Object[] inv = inventory.toArray();
    builder.append("\n~~~ INVENTORY (" + inv.length + "/15)  ~~~\n");

    for (int i = 0; i < inv.length; i++) {
      builder.append("~ " + (i + 1) + ". ");
      builder.append(((Item) inv[i]).getName() + "\n");
    }

    builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~");

    System.out.println(builder.toString());
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
  public void showEquippedItems() {
    StringBuilder builder = new StringBuilder();

    builder.append("\n~~~ Equipped Items ~~~\n");

    equippedItems.forEach((k, v) -> {
      builder.append("~ " + k.toString() + ": ");
      builder.append(v.getName() + "\n");
    });

    builder.append("~~~~~~~~~~~~~~~~~~~~~~");

    System.out.println(builder.toString());
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
  public void wield(Weapon weapon) {
    try {
      this.wield(weapon, inventory, equippedItems, level, heroType);
    } catch (Throwable err) {
      System.out.println(err.getMessage());
    }
  }

  private void wield(Weapon weapon,
      List<Item> inventory,
      EnumMap<EquipmentSlots, Equipment> equippedItems,
      int heroLevel,
      HeroType heroType)
      throws InvalidWeaponException, InventoryException {

    try {
      if (!inventory.remove(weapon)) {
        throw new InventoryException(InventoryExceptionMsg.NOT_FOUND);
      }

      if (weapon.getLevelRequirement() > heroLevel) {
        throw new InvalidWeaponException(InvalidWeaponExceptionMsg.LEVEL_REQUIREMENT);
      }

      if (!((EnumSet<WeaponTypes>) heroType.getValidWeaponTypes()).contains(weapon.getWeaponType())) {
        throw new InvalidWeaponException(InvalidWeaponExceptionMsg.WRONG_TYPE);
      }

      // Replace with unequip method
      Item unwielded = equippedItems.remove(EquipmentSlots.WEAPON);
      if (unwielded instanceof Weapon) {
        addToInventory((Weapon) unwielded);
      }
      // Replace with unequip method ^^^^

      equippedItems.put(EquipmentSlots.WEAPON, weapon);
      System.out.println("wielded weapon");
      System.out.println(equippedItems);
    } catch (InvalidWeaponException err) {
      throw err;
    }
  };

  private Hero(HeroBuilder builder) {
    name = builder.name;
    heroType = builder.heroType;

    heroType.init();
    heroAttributes = heroType.getStartingAttributes();

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
