package Interfaces;

import java.util.EnumMap;
import java.util.EnumSet;

import ENUMS.ArmorTypes;
import ENUMS.HeroAttributes;
import ENUMS.WeaponTypes;
import Equipment.Weapon;

public interface GameCharacter {

  void display();

  void wield(Weapon weapon);

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
