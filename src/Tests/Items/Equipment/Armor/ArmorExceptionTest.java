package Tests.Items.Equipment.Armor;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

import Game.Components.Exceptions.InvalidEquipmentException;
import Game.Components.GameCharacters.Hero.Hero;
import Game.Components.GameCharacters.Hero.HeroType;
import Game.Components.Items.Equipment.Armor.Armor;
import Game.Components.Items.Equipment.Armor.ArmorItems.Cloth;
import Game.Components.Items.Equipment.Armor.ArmorItems.Mail;

public class ArmorExceptionTest {

  // NOTE: Inventory slot 0 is taken up by starter weapon.

  @Test
  public void GivenHeroType_WhenInsufficientLevel_ShouldThrowEquipmentException() {
    Hero testHero = new Hero.HeroBuilder("Hero TestName", HeroType.MAGE).build();
    Armor insufficientLevelArmor = new Armor.ArmorBuilder(Cloth.WRINKLY_ROBE_BOTTOMS).build();

    assertThrows(InvalidEquipmentException.class, () -> {
      testHero.addToInventory(insufficientLevelArmor);
      testHero.equip(1);
    });
  }

  @Test
  public void GivenHeroType_WhenWrongType_ShouldThrowEquipmentException() {
    Hero testHero = new Hero.HeroBuilder("Hero TestName", HeroType.MAGE).build();
    Armor wrongTypeArmor = new Armor.ArmorBuilder(Mail.ROYAL_MAIL).build();

    assertThrows(InvalidEquipmentException.class, () -> {
      testHero.addToInventory(wrongTypeArmor);
      testHero.equip(1);
    });
  }

}
