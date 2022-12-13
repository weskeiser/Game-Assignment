package Game.GameCharacters.Interfaces;

import java.util.EnumMap;
import java.util.List;

import Game.Exceptions.InvalidEquipmentException;
import Game.Items.Item;
import Game.Items.LootableItem;
import Game.Items.Equipment.*;
import Game.Items.Equipment.Weapon.Weapon;

public interface CombatManager {

  int getLevel();

  double getHealth();

  void defend(double maxHit, CombatManager foe);

  void takeDamage(double damage);

  double getMaxHit();

  default double getMaxHit(CombatManager gameCharacter) {
    Weapon equippedWeapon = null;

    try {
      Weapon fetched = ((EquipmentManager) gameCharacter).getEquippedWeapon();
      equippedWeapon = fetched;
    } catch (InvalidEquipmentException err) {
      // Expected behaviour, no handling needed.
    }

    // ???????
    CharacterAttribute damagingAttribute = ((GameCharacter) gameCharacter).getCharacterType().getDamagingAttribute();
    int attributeDamage = ((AttributeManager) gameCharacter).getHeroAttributes().get(damagingAttribute);
    // ???????

    double weaponDamage = (equippedWeapon != null) ? equippedWeapon.getDamageMultiplier() : 1;

    System.out.println("Calculated damage: " + weaponDamage * (1 + attributeDamage / 100));
    return weaponDamage * (1 + attributeDamage / 100);
  }

  public void finalBlow(CombatManager defeator);

  default void finalBlow(CombatManager defeator, double health, CombatManager defeatedBy,
      EnumMap<EquipmentSlot, Equippable> equippedItems, List<LootableItem> remains, List<Item> inventory) {

    health = 0;
    defeatedBy = defeator;

    equippedItems.values().forEach((item) -> remains.add((LootableItem) item));
    equippedItems.clear();

    inventory.forEach((item) -> remains.add((LootableItem) item));
    inventory.clear();
  };

}