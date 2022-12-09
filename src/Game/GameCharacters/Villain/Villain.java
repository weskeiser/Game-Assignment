package Game.GameCharacters.Villain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;

import Game.Exceptions.InvalidWeaponException;
import Game.Exceptions.LootException;
import Game.GameCharacters.CharacterType;
import Game.GameCharacters.CombatManager;
import Game.GameCharacters.GameCharacter;
import Game.GameCharacters.Remains;
import Game.GameCharacters.Hero.CharacterAttribute;
import Game.GameCharacters.Hero.HeroType;
import Game.Items.InventoryManager;
import Game.Items.Item;
import Game.Items.Lootable;
import Game.Items.Equipment.Equipment;
import Game.Items.Equipment.EquipmentManager;
import Game.Items.Equipment.EquipmentSlot;
import Game.Items.Equipment.Weapon.Weapon;

public class Villain implements GameCharacter, InventoryManager, EquipmentManager, CombatManager, Remains {

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
  private EnumMap<EquipmentSlot, Equipment> equippedItems = new EnumMap<EquipmentSlot, Equipment>(
      EquipmentSlot.class);

  @Override
  public Lootable takeItem(Lootable lootItem) throws LootException {
    boolean taken = remains.remove(lootItem);
    if (!taken)
      throw new LootException(LootException.Messages.NOT_FOUND);
    return lootItem;
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
  public void defend(double maxHit, GameCharacter foe) {
    // TODO Auto-generated method stub
  }

  @Override
  public void showInventory() {
    // TODO Auto-generated method stub
  }

  @Override
  public void showLevel() {
    System.out.println(level);
  }

  @Override
  public void display() {
    // TODO Auto-generated method stub

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
  public void equip(int inventoryIndex) {
    // equip directly
  }

  @Override
  public void unEquip(EquipmentSlot equipmentSlot) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addToInventory(Item item) {
    // TODO Auto-generated method stub

  }

  @Override
  public CharacterType getCharacterType() {
    return heroType;
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
  public double getMaxHit() {
    return getMaxHit(this);
  }

  @Override
  public Weapon getEquippedWeapon() throws InvalidWeaponException {
    Weapon equippedWeapon = (Weapon) equippedItems.get(EquipmentSlot.WEAPON);

    if (equippedWeapon == null) {
      throw new InvalidWeaponException(InvalidWeaponException.Messages.NO_WEAPON);
    }

    return equippedWeapon;

  }

  private Villain(VillainBuilder builder) {
    name = builder.name;
    heroType = builder.heroType;

    heroAttributes = heroType.init();

    // addToInventory(new Weapon.WeaponBuilder(heroType.starterWeapon).build());
  }

  public static class VillainBuilder {
    private String name;
    private HeroType heroType;

    public VillainBuilder(String name, HeroType heroType) {
      this.name = name;
      this.heroType = heroType;
    }

    public Villain build() {
      return new Villain(this);
    }
  }

}
