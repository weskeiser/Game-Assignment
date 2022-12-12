package Game.GameCharacters;

import java.util.EnumMap;
import java.util.List;

import Game.Exceptions.InvalidEquipmentException;
import Game.Exceptions.InventoryException;
import Game.GameCharacters.Hero.HeroType;
import Game.Items.Item;
import Game.Items.LootableItem;
import Game.Items.Equipment.EquipmentSlot;
import Game.Items.Equipment.Equippable;
import Game.Items.Equipment.Armor.Armor;
import Game.Items.Equipment.Weapon.Weapon;

public interface GameCharacter {
  public static final String resetC = "\u001B[0m";
  public static final String redC = "\u001b[35m";
  public static final String greenC = "\u001B[32m";
  public static final String blueC = "\u001B[34m";
  public static final String yellowC = "\u001b[33m";

  default EnumMap<CharacterAttribute, Integer> getTotalAttributes(
      HeroType heroType, EnumMap<EquipmentSlot, Equippable> equippedItems) {

    EnumMap<CharacterAttribute, Integer> totalAttributes = heroType.getLevelingAttributes().clone();

    equippedItems.forEach((equipmentSlot, charAttrVal) -> {
      if (equipmentSlot == EquipmentSlot.WEAPON)
        return;

      EnumMap<CharacterAttribute, Integer> armorAttributes = ((Armor) charAttrVal).getArmorAttributes();

      armorAttributes.forEach((heroAttrKey, armorAttrVal) -> totalAttributes.put(
          heroAttrKey,
          totalAttributes.get(heroAttrKey) + armorAttrVal));
    });

    return totalAttributes;
  };

  default void showCharacterAttributes(EnumMap<CharacterAttribute, Integer> characterAttributes) {
    StringBuilder keyBuilder = new StringBuilder();
    StringBuilder valueBuilder = new StringBuilder();
    keyBuilder.append(redC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    keyBuilder.append("\n" + blueC + "    ~");

    characterAttributes.forEach((k, v) -> {
      keyBuilder.append(" " + greenC + k + blueC + " ~");
      valueBuilder.append(yellowC + v + "           ");
    });

    System.out.println(keyBuilder.toString() + "\n         " + valueBuilder.toString());
  }

  void display();

  void showHealth();

  void finalBlow(GameCharacter defeator);

  default void finalBlow(GameCharacter defeator, double health, GameCharacter defeatedBy,
      EnumMap<EquipmentSlot, Equippable> equippedItems, List<LootableItem> remains, List<Item> inventory) {

    health = 0;

    defeatedBy = defeator;

    equippedItems.values().forEach((item) -> remains.add((LootableItem) item));
    equippedItems.clear();

    inventory.forEach((item) -> remains.add((LootableItem) item));
    inventory.clear();
  };

  void equip(int inventoryIndex) throws InvalidEquipmentException, InventoryException;

  void equip(Equippable equipment) throws InvalidEquipmentException, InventoryException;

  void unEquip(EquipmentSlot equipmentSlot) throws InvalidEquipmentException, InventoryException;

  void addToInventory(Item item) throws InventoryException;

  void showLevel();

  CharacterType getCharacterType();

  EnumMap<CharacterAttribute, Integer> getHeroAttributes();

  String getName();

  int getLevel();

  double getMaxHit();

  void defend(double maxHit, GameCharacter foe);

  void takeDamage(double damage);

  Weapon getEquippedWeapon() throws InvalidEquipmentException;

}
