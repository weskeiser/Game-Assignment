package GameCharacters;

import java.util.EnumMap;
import GameCharacters.Hero.HeroAttribute;
import Items.Item;
import Items.Lootable;
import Items.Equipment.Equipment;
import Items.Equipment.EquipmentSlot;

public interface GameCharacter {

  void display();

  void defendYourself(double maxHit);

  void attack(GameCharacter foe);

  void equip(Equipment equipment);

  void unEquip(EquipmentSlot equipmentSlot);

  void dropItem(Item item);

  // void loot(Lootable item);
  void loot(Item item);

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
