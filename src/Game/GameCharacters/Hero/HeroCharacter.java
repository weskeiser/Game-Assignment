package Game.GameCharacters.Hero;

import java.util.EnumMap;
import java.util.List;

import Game.GameCharacters.GameCharacter;
import Game.Items.Item;
import Game.Items.Equipment.EquipmentSlot;
import Game.Items.Equipment.Equippable;

public interface HeroCharacter extends GameCharacter {
  int getFreeInventorySlots();

  void showInventory();

  void showEquippedItems();

  int levelUp();

  void gainExperience(int expGain);

  void showHeroAttributes();

  default void showInventory(List<Item> inventory) {
    StringBuilder builder = new StringBuilder();
    Object[] inv = inventory.toArray();

    builder.append(blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    builder.append("\n" + blueC + "~~~~~~~~~~~~~~" + resetC + " INVENTORY " + redC + "(" + resetC + inv.length + "/15"
        + redC + ")" + blueC + " ~~~~~~~~~~~~~~\n");

    for (int i = 0; i < inv.length; i++) {
      builder.append(blueC + "~~~~~~~~~~~~~~ " + redC + (i + 1) + ". ");
      builder.append(yellowC + ((Item) inv[i]).getName() + "\n");
    }

    builder.append(blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    System.out.println(builder.toString());
  }

  default void showEquippedItems(EnumMap<EquipmentSlot, Equippable> equippedItems) {
    StringBuilder builder = new StringBuilder();

    builder.append(blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    builder.append("\n" + blueC + "~~~~~~~~~~~~~~    " + resetC + " EQUIPPED " + blueC + "    ~~~~~~~~~~~~~~\n");

    equippedItems.forEach((k, v) -> builder.append(blueC + "~~~~~~~~~~~~~~ " + redC + k.toString() + ": ")
        .append(yellowC + v.getName() + "\n"));

    builder.append(blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    System.out.println(builder.toString());
  }
}
