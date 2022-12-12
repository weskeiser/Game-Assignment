package Game.GameCharacters.Villain;

import java.util.*;

import Game.Exceptions.LootException;
import Game.GameCharacters.*;
import Game.GameCharacters.Hero.HeroType;
import Game.Items.*;
import Game.Items.Equipment.*;
import Game.Items.Equipment.Weapon.Weapon;

public class Villain implements GameCharacter, InventoryManager, EquipmentManager, CombatManager, Remains {

  private String name;
  private HeroType heroType;

  private GameCharacter defeatedBy = null;
  private List<LootableItem> remains;

  private double health = 100;
  private int experienceToLevel = 30;
  private int experience = 0;
  private int level = 1;
  private EnumMap<CharacterAttribute, Integer> characterAttributes;

  private List<Item> inventory = new ArrayList<Item>(15);
  private EnumMap<EquipmentSlot, Equippable> equippedItems = new EnumMap<EquipmentSlot, Equippable>(
      EquipmentSlot.class);

  @Override
  public LootableItem takeItem(LootableItem lootItem) throws LootException {
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

    Iterator<Equippable> equpmentIterator = equippedItems.values().iterator();
    while (equpmentIterator.hasNext()) {
      remains.add((LootableItem) equpmentIterator.next());
    }
    equippedItems.clear();

    inventory.forEach((item) -> {
      remains.add((LootableItem) item);
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
    return characterAttributes;
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

    characterAttributes = heroType.init();

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
