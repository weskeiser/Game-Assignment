package Game.GameCharacters;

import java.util.EnumMap;

import Game.GameCharacters.Hero.HeroAttribute;
import Game.Items.Item;
import Game.Items.Lootable;
import Game.Items.Equipment.Equipment;
import Game.Items.Equipment.EquipmentSlot;

public interface GameCharacter {

  void display();

  void defendYourself(double maxHit);

  void attack(GameCharacter foe);

  void equip(Equipment equipment);

  void unEquip(EquipmentSlot equipmentSlot);

  void dropItem(Item item);

  // void loot(Lootable item);
  void loot(Lootable loot);

  void addToInventory(Item item);

  void showInventory();

  void showEquippedItems();

  void showLevel();

  EnumMap<HeroAttribute, Integer> getHeroAttributes();

  Item getInventoryItemByIdx(int index);

  String getName();

  int getLevel();

  int levelUp();

}
