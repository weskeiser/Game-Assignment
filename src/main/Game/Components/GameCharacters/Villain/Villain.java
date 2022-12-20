package main.Game.Components.GameCharacters.Villain;

import java.util.*;

import main.Game.Components.Exceptions.InvalidEquipmentException;
import main.Game.Components.Exceptions.InventoryException;
import main.Game.Components.Exceptions.InventoryException.InventoryErrMessages;
import main.Game.Components.GameCharacters.Interfaces.*;
import main.Game.Components.GameCharacters.Remains.Remains;
import main.Game.Components.Items.GameItem;
import main.Game.Components.Items.Equipment.*;
import main.Game.Components.Items.Equipment.Armor.Armor;
import main.Game.Components.Items.Equipment.Weapon.Weapon;
import utils.CLR;

public class Villain implements InventoryManager, EquipmentManager, Attacker, Defender {

  private String name;
  private VillainType villainType;

  private int level = 600;
  private double health = 10;
  private EnumMap<CharacterAttribute, Integer> characterAttributes;

  // Combat
  private int attackCooldown = 0;
  private Defender currentlyAttacking = null;
  private Attacker defeatedBy = null;
  private List<Remains> lootableRemains = new ArrayList<>();

  // Items
  private List<GameItem> inventory = new ArrayList<GameItem>();
  private EnumMap<EquipmentSlot, Equippable> equippedItems = new EnumMap<>(EquipmentSlot.class);

  @Override
  public String getName() {
    return name;
  }

  public CharacterType getCharacterType() {
    return villainType;
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
    return villainType.getAttackAttribute();
  }

  @Override
  public int getAttackSpeed() {
    var dexterityAttributeLevel = characterAttributes.get(CharacterAttribute.DEXTERITY);

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

    equip(equipment, inventory, equippedItems, level, villainType);
  }

  @Override
  public void equip(int inventoryIndex) throws InvalidEquipmentException, InventoryException {

    var itemExistingInInventory = (Equippable) findInventoryItem(inventoryIndex);

    equip(itemExistingInInventory, inventory, equippedItems, level, villainType);
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

    return Optional.ofNullable((Weapon) equippedItems.get(EquipmentSlot.WEAPON));
  }

  // Attributes related
  @Override
  public EnumMap<CharacterAttribute, Integer> getCharacterAttributes() {
    return characterAttributes;
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
      armorAttributes.forEach((armorAttrKey, armorAttrVal) -> defensiveAttributes.put(
          armorAttrKey,
          defensiveAttributes.getOrDefault(armorAttrKey, 0) + armorAttrVal));
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

  public void display() {
    StringBuilder displayBuilder = new StringBuilder();
    displayBuilder
        .append("\n" + CLR.redC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        .append("\n" + CLR.redC + "/~/~/" + "   >> "
            + CLR.resetC + name
            + CLR.redC + " <<   |  "
            + CLR.resetC + "lvl."
            + CLR.yellowC + level + " "
            + CLR.resetC + villainType.toString());
    System.out.println(displayBuilder.toString());

    StringBuilder keyBuilder = new StringBuilder();
    StringBuilder valueBuilder = new StringBuilder();
    keyBuilder.append(CLR.redC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    keyBuilder.append("\n" + CLR.blueC + "    ~");

    // Attributes
    characterAttributes.forEach((k, v) -> {
      keyBuilder.append(" " + CLR.greenC + k + CLR.blueC + " ~");
      valueBuilder.append(CLR.yellowC + v + "           ");
    });

    System.out.println(keyBuilder.toString() + "\n         " + valueBuilder.toString());

    // Inventory
    StringBuilder builder = new StringBuilder();
    Object[] inv = inventory.toArray();

    builder.append(CLR.blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    builder.append("\n" + CLR.blueC + "~~~~~~~~~~~~~~" + CLR.resetC + " INVENTORY " + CLR.redC + "(" + CLR.resetC
        + inv.length + "/15"
        + CLR.redC + ")" + CLR.blueC + " ~~~~~~~~~~~~~~\n");

    for (int i = 0; i < inv.length; i++) {
      builder.append(CLR.blueC + "~~~~~~~~~~~~~~ " + CLR.redC + (i + 1) + ". ");
      builder.append(CLR.yellowC + ((GameItem) inv[i]).getName() + "\n");
    }

    builder.append(CLR.blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    System.out.println(builder.toString());

    System.out.println();

    // Equipment

    StringBuilder equipmentBuilder = new StringBuilder();

    equipmentBuilder.append(CLR.blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    equipmentBuilder.append(
        "\n" + CLR.blueC + "~~~~~~~~~~~~~~    " + CLR.resetC + " EQUIPPED " + CLR.blueC + "    ~~~~~~~~~~~~~~\n");

    equippedItems
        .forEach((k, v) -> equipmentBuilder.append(CLR.blueC + "~~~~~~~~~~~~~~ " + CLR.redC + k.toString() + ": ")
            .append(CLR.yellowC + v.getName() + "\n"));

    equipmentBuilder.append(CLR.blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    System.out.println(equipmentBuilder.toString());

  }

  private Villain(VillainBuilder builder) {
    name = builder.name;
    villainType = builder.villainType;

    characterAttributes = villainType.init();

    try {
      addToInventory(builder.weapon);
      equip(builder.weapon);
    } catch (InvalidEquipmentException err) {
      System.out.println(err.getMessage());
    } catch (InventoryException err) {
      System.out.println(err.getMessage());

    }
  }

  public static class VillainBuilder {
    private String name;
    private VillainType villainType;

    Weapon weapon;

    public VillainBuilder provideWeapon(Weapon weapon) {
      this.weapon = weapon;

      return this;
    }

    public VillainBuilder(String name, VillainType villainType) {
      this.name = name;
      this.villainType = villainType;
    }

    public Villain build() {
      return new Villain(this);
    }
  }

}
