package GameCharacters;

import java.util.EnumMap;
import java.util.EnumSet;

import GameCharacters.Hero.HeroAttributes;
import Items.Item;
import Items.Equipment.Equipment;
import Items.Equipment.Armor.ArmorTypes;
import Items.Equipment.Weapon.WeaponTypes;

public interface GameCharacter {

  void display();

  void equip(Equipment equipment);

  void dropItem(Item item);

  void addToInventory(Item item);

  void showInventory();

  void loot(Item item);

  void showEquippedItems();

  EnumSet<WeaponTypes> getValidWeaponTypes();

  EnumSet<ArmorTypes> getValidArmorTypes();

  EnumMap<HeroAttributes, Integer> getHeroAttributes();

  Item getInventoryItemByIdx(int index);

  String getName();

  int getLevel();

  int levelUp();

}
