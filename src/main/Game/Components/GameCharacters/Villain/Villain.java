package main.Game.Components.GameCharacters.Villain;

import java.util.*;

import main.Game.Components.Exceptions.InvalidEquipmentException;
import main.Game.Components.Exceptions.InventoryException;
import main.Game.Components.Exceptions.InventoryException.InventoryErrMessages;
import main.Game.Components.GameCharacters.Hero.HeroType;
import main.Game.Components.GameCharacters.Interfaces.*;
import main.Game.Components.GameCharacters.Remains.Remains;
import main.Game.Components.Items.GameItem;
import main.Game.Components.Items.Equipment.*;
import main.Game.Components.Items.Equipment.Armor.Armor;
import main.Game.Components.Items.Equipment.Weapon.Weapon;

public class Villain implements InventoryManager, EquipmentManager, Attacker, Defender {

  private String name;
  private HeroType heroType;

  private int level = 1;
  private double health = 10;
  private EnumMap<CharacterAttribute, Integer> villainAttributes;

  // Combat
  private int attackCooldown = 0;
  private Defender currentlyAttacking = null;
  private Attacker defeatedBy = null;
  private List<Remains> lootableRemains = new ArrayList<>();

  // Items
  private List<GameItem> inventory = new ArrayList<GameItem>();
  private EnumMap<EquipmentSlot, Equippable> equippedItems = new EnumMap<>(EquipmentSlot.class);

  public String getName() {
    return name;
  }

  public CharacterType getCharacterType() {
    return heroType;
  }

  @Override
  public int getLevel() {
    return level;
  }

  // Combat related: Attacks

  @Override
  public double getHealth() {
    return health;
  }

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
    var dexterityAttributeLevel = villainAttributes.get(CharacterAttribute.DEXTERITY);

    return dexterityAttributeLevel / 100;
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
    var deflectionChance = getDefensiveAttributes(equippedItems).get(attackAttribute);
    var roll100 = new Random().nextInt(100) + 1;

    var deflected = false;

    if (deflectionChance >= roll100)
      deflected = true;

    return deflected;
  };

  @Override
  public Remains surrenderValuables() {

    var loot = new ArrayList<GameItem>();

    equippedItems.values().forEach(loot::add);
    inventory.forEach(loot::add);

    equippedItems.clear();
    inventory.clear();

    return new Remains(loot, defeatedBy);
  }

  // Item related

  @Override
  public GameItem findInventoryItem(int index) throws InventoryException {
    try {
      return inventory.get(index);
    } catch (IndexOutOfBoundsException err) {
      throw new InventoryException(InventoryErrMessages.NOT_FOUND);
    }
  }

  @Override
  public void addToInventory(GameItem item) throws InventoryException {
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

    var itemExistingInInventory = (Equippable) findInventoryItem(inventoryIndex);

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

    var equippedWeapon = (Weapon) equippedItems.get(EquipmentSlot.WEAPON);

    return Optional.ofNullable(equippedWeapon);
  }

  // Attributes related
  @Override
  public EnumMap<CharacterAttribute, Integer> getCharacterAttributes() {
    return villainAttributes;
  }

  public EnumMap<CharacterAttribute, Integer> getDefensiveAttributes(
      EnumMap<EquipmentSlot, Equippable> equippedItems) {

    EnumMap<CharacterAttribute, Integer> defensiveAttributes = new EnumMap<>(CharacterAttribute.class);

    // For each equipped item
    equippedItems.forEach((equipmentSlot, charAttrVal) -> {

      // If not weapon
      if (equipmentSlot == EquipmentSlot.WEAPON)
        return;

      // Get armor attributes
      var armorAttributes = ((Armor) charAttrVal).getArmorAttributes();

      // Add each attribute to total (defensiveAttributes)
      armorAttributes.forEach((heroAttrKey, armorAttrVal) -> defensiveAttributes.put(
          heroAttrKey,
          defensiveAttributes.getOrDefault(heroAttrKey, 0) + armorAttrVal));
    });

    if (defensiveAttributes.isEmpty()) {
      return new EnumMap<>(Map.ofEntries(
          Map.entry(CharacterAttribute.STRENGTH, 0),
          Map.entry(CharacterAttribute.DEXTERITY, 0),
          Map.entry(CharacterAttribute.INTELLIGENCE, 0)));
    }

    return defensiveAttributes;
  }

  @Override
  public void gainExperience(double expGain) {
  };

  private Villain(VillainBuilder builder) {
    name = builder.name;
    heroType = builder.heroType;

    villainAttributes = heroType.init();
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
