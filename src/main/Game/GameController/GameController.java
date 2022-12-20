package main.Game.GameController;

import java.util.HashSet;

import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.GameCharacters.Hero.HeroType;
import main.Game.Components.GameCharacters.Villain.Villain;
import main.Game.Components.GameCharacters.Villain.VillainType;
import main.Game.Components.Items.Equipment.Equippable;
import main.Game.Components.Items.Equipment.Weapon.Weapon;
import main.Game.Components.Items.Equipment.Weapon.WeaponItem;

public class GameController {

  public Hero newWarrior(String name) {
    return new Hero.HeroBuilder(name, HeroType.WARRIOR).build();
  }

  public Hero newRogue(String name) {
    return new Hero.HeroBuilder(name, HeroType.ROGUE).build();
  }

  public Hero newRanger(String name) {
    return new Hero.HeroBuilder(name, HeroType.RANGER).build();
  }

  public Hero newMage(String name) {
    return new Hero.HeroBuilder(name, HeroType.MAGE).build();
  }

  public Villain newVillain(String name, VillainType villainType, WeaponItem weaponItem) {
    return new Villain.VillainBuilder(name, villainType)
        .provideWeapon(new Weapon.WeaponBuilder(weaponItem).build()).build();
  }

  public HashSet<Villain> newGang(HashSet<String> names, VillainType villainType, WeaponItem weaponItem) {

    var gang = new HashSet<Villain>();

    names.forEach(name -> gang.add(newVillain(name, villainType, weaponItem)));

    return gang;
  }

  public void equip(int inventoryIndex, Hero character) {
    try {
      character.equip(inventoryIndex);
    } catch (Throwable err) {
      System.out.println(err);
    }
  }

  public void equip(Equippable equipment, Hero character) {
    try {
      character.equip(equipment);
    } catch (Throwable err) {
      System.out.println(err);
    }
  }

  // public void lootRemains(LootableRemains remains, Hero looter, Item lootItem)
  // {
  // try {
  // if (looter.getFreeInventorySlots() <= 0)
  // throw new LootException(LootException.Messages.FULL_INVENTORY);

  // if (looter != remains.getDefeator())
  // throw new LootException(LootException.Messages.NOT_YOURS);

  // remains.takeItem(lootItem);
  // looter.addToInventory((Item) lootItem);

  // } catch (LootException err) {
  // System.out.println(err.getMessage());

  // } catch (InventoryException err) {
  // System.out.println(err.getMessage());
  // }
  // }

}
