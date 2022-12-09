package Game.GameCharacters;

import java.util.EnumMap;
import java.util.List;

import Game.Exceptions.InvalidWeaponException;
import Game.GameCharacters.Hero.CharacterAttribute;
import Game.GameCharacters.Hero.HeroType;
import Game.Items.Item;
import Game.Items.Equipment.Equipment;
import Game.Items.Equipment.EquipmentSlot;
import Game.Items.Equipment.Armor.Armor;
import Game.Items.Equipment.Weapon.Weapon;

public interface GameCharacter {
  public static final String resetC = "\u001B[0m";
  public static final String redC = "\u001b[35m";
  public static final String greenC = "\u001B[32m";
  public static final String blueC = "\u001B[34m";
  public static final String yellowC = "\u001b[33m";

  default EnumMap<CharacterAttribute, Integer> getTotalAttributes(HeroType heroType,
      EnumMap<EquipmentSlot, Equipment> equippedItems) {
    EnumMap<CharacterAttribute, Integer> totalAttributes = heroType.getLevelAttributes().clone();

    equippedItems.forEach((equipmentSlot, lvlAttrVal) -> {
      if (equipmentSlot == EquipmentSlot.WEAPON)
        return;

      EnumMap<CharacterAttribute, Integer> armorAttributes = ((Armor) lvlAttrVal).getArmorAttributes();

      armorAttributes.forEach((heroAttrKey, armorAttrVal) -> {
        totalAttributes.put(
            heroAttrKey,
            totalAttributes.get(heroAttrKey) + armorAttrVal);
      });
    });

    System.out.println(totalAttributes);

    return totalAttributes;
  };

  void display();

  void finalBlow(GameCharacter defeator);

  void defend(double maxHit, GameCharacter foe);

  void equip(int inventoryIndex);

  void unEquip(EquipmentSlot equipmentSlot);

  void addToInventory(Item item);

  void showLevel();

  CharacterType getCharacterType();

  EnumMap<CharacterAttribute, Integer> getHeroAttributes();

  String getName();

  int getLevel();

  double getMaxHit();

  Weapon getEquippedWeapon() throws InvalidWeaponException;

}
