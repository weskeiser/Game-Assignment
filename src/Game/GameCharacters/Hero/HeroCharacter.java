package Game.GameCharacters.Hero;

import java.util.EnumMap;
import java.util.List;

import Game.GameCharacters.GameCharacter;
import Game.Items.Item;
import Game.Items.Equipment.Equipment;
import Game.Items.Equipment.EquipmentSlot;

public interface HeroCharacter extends GameCharacter {
  int getFreeInventorySlots();

  void dropItem(Item item);

  void showInventory();

  void showEquippedItems();

  int levelUp();

  void gainExperience(int expGain);

  default void showInventory(List<Item> inventory) {
    StringBuilder builder = new StringBuilder();
    Object[] inv = inventory.toArray();

    builder.append("\n" + blueC + "~~~~~~~~~~~~" + resetC + " INVENTORY " + redC + "(" + yellowC + inv.length + "/15"
        + redC + ")" + blueC + " ~~~~~~~~~~~~\n");

    for (int i = 0; i < inv.length; i++) {
      builder.append(blueC + "~~~~~~~~~~~~ " + redC + (i + 1) + ". ");
      builder.append(yellowC + ((Item) inv[i]).getName() + "\n");
    }

    builder.append(blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    System.out.println(builder.toString());
  }

  default void showEquippedItems(EnumMap<EquipmentSlot, Equipment> equippedItems) {
    StringBuilder builder = new StringBuilder();

    builder.append("\n" + blueC + "~~~~~~~~~~~~" + resetC + " EQUIPPED " + blueC + "~~~~~~~~~~~~~~~~~~~~\n");

    equippedItems.forEach((k, v) -> {
      builder.append(blueC + "~~~~~~~~~~~~ " + redC + k.toString() + ": ");
      builder.append(yellowC + v.getName() + "\n");
    });

    builder.append(blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    System.out.println(builder.toString());
  }
}
