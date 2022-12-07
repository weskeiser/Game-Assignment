package Game.GameCharacters;

import java.util.EnumMap;

import Game.Exceptions.InvalidWeaponException;
import Game.GameCharacters.Hero.HeroAttribute;
import Game.Items.Item;
import Game.Items.Equipment.Equipment;
import Game.Items.Equipment.EquipmentSlot;
import Game.Items.Equipment.Weapon.Weapon;

public interface GameCharacter {

  void display();

  void finalBlow(GameCharacter defeator);

  void defend(double maxHit, GameCharacter foe);

  void equip(Equipment equipment);

  void unEquip(EquipmentSlot equipmentSlot);

  void dropItem(Item item);

  void addToInventory(Item item);

  void showInventory();

  void showEquippedItems();

  void showLevel();

  CharacterType getCharacterType();

  EnumMap<HeroAttribute, Integer> getHeroAttributes();

  String getName();

  double getMaxHit();

  public Weapon getEquippedWeapon() throws InvalidWeaponException;

  int getLevel();

  int levelUp();

}
