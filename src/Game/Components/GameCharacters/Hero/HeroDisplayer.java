package Game.Components.GameCharacters.Hero;

import java.util.EnumMap;
import java.util.List;

import Game.Components.Items.Item;
import Game.Components.Items.Equipment.EquipmentSlot;
import Game.Components.Items.Equipment.Equippable;
import utils.CLR;

public interface HeroDisplayer {
  void showInventory();

  void showHeroAttributes();

  void showHealth();

  void showLevel();

  void display();

  void showEquippedItems();

  default void showInventory(List<Item> inventory) {
    StringBuilder builder = new StringBuilder();
    Object[] inv = inventory.toArray();

    builder.append(CLR.blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    builder.append("\n" + CLR.blueC + "~~~~~~~~~~~~~~" + CLR.resetC + " INVENTORY " + CLR.redC + "(" + CLR.resetC
        + inv.length + "/15"
        + CLR.redC + ")" + CLR.blueC + " ~~~~~~~~~~~~~~\n");

    for (int i = 0; i < inv.length; i++) {
      builder.append(CLR.blueC + "~~~~~~~~~~~~~~ " + CLR.redC + (i + 1) + ". ");
      builder.append(CLR.yellowC + ((Item) inv[i]).getName() + "\n");
    }

    builder.append(CLR.blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    System.out.println(builder.toString());
  }

  default void showEquippedItems(EnumMap<EquipmentSlot, Equippable> equippedItems) {
    StringBuilder builder = new StringBuilder();

    builder.append(CLR.blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    builder.append(
        "\n" + CLR.blueC + "~~~~~~~~~~~~~~    " + CLR.resetC + " EQUIPPED " + CLR.blueC + "    ~~~~~~~~~~~~~~\n");

    equippedItems.forEach((k, v) -> builder.append(CLR.blueC + "~~~~~~~~~~~~~~ " + CLR.redC + k.toString() + ": ")
        .append(CLR.yellowC + v.getName() + "\n"));

    builder.append(CLR.blueC + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    System.out.println(builder.toString());
  }

}
