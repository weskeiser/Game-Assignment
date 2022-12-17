package Game.Components.GameCharacters.Villain;

import java.util.EnumMap;

import Game.Components.GameCharacters.Hero.HeroType;
import Game.Components.GameCharacters.Interfaces.*;
import Game.Components.Items.Equipment.EquipmentManager;

public class Villain implements InventoryManager, EquipmentManager, Attacker, Defender {
  private String name;
  private HeroType heroType;

  private EnumMap<CharacterAttribute, Integer> villainAttributes;

  private Villain(VillainBuilder builder) {
    name = builder.name;
    heroType = builder.heroType;

    villainAttributes = heroType.init();
  }

  public static class VillainBuilder {
    private String name;
    private HeroType heroType;

    public VillainBuilder(String name, HeroType heroType) {
      this.name = name;
      this.heroType = heroType;
    }

    public Villain build() {
      return new Villain(this);
    }
  }

}
