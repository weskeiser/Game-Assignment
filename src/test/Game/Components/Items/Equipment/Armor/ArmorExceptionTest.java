package test.Game.Components.Items.Equipment.Armor;

import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import main.Game.Components.Exceptions.InvalidEquipmentException;
import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.GameCharacters.Hero.HeroType;
import main.Game.Components.Items.Equipment.Armor.Armor;
import main.Game.Components.Items.Equipment.Armor.ArmorItem;
import main.Game.Components.Items.Equipment.Armor.ArmorItems.Cloth;
import main.Game.Components.Items.Equipment.Armor.ArmorItems.Mail;

@RunWith(Parameterized.class)
public class ArmorExceptionTest {

  private Hero testHero;

  private Armor insufficientLevelArmor;
  private Armor wrongTypeArmor;

  // Params for constructor
  @Parameterized.Parameters

  public static Collection<Object[]> testParams() {

    return Arrays.asList(
        new Object[][] {
            { HeroType.WARRIOR, Mail.ROYAL_MAIL, Cloth.IRONED_ROBE_TOP },
            { HeroType.ROGUE, Mail.ROYAL_MAIL, Cloth.IRONED_ROBE_TOP },
            { HeroType.RANGER, Mail.ROYAL_MAIL, Cloth.IRONED_ROBE_TOP },
            { HeroType.MAGE, Cloth.WRINKLY_ROBE_BOTTOMS, Mail.STEEL_CHAIN_HOOD },
        });
  }

  // Constructor
  public ArmorExceptionTest(HeroType heroType, ArmorItem insufficientLevelArmor, ArmorItem wrongTypeArmor) {

    this.testHero = new Hero.HeroBuilder("TestHero Name", heroType).build();

    this.insufficientLevelArmor = new Armor.ArmorBuilder(insufficientLevelArmor).build();
    this.wrongTypeArmor = new Armor.ArmorBuilder(wrongTypeArmor).build();
  }

  @Test
  public void CreateHero_EquipHigherLevelArmor_ThrowsEquipmentException() {

    assertThrows(InvalidEquipmentException.class, () -> {
      testHero.addToInventory(insufficientLevelArmor);
      testHero.equip(insufficientLevelArmor);
    });
  }

  @Test
  public void CreateHero_EquipArmorOfWrongType_ThrowsEquipmentException() {

    assertThrows(InvalidEquipmentException.class, () -> {
      testHero.addToInventory(wrongTypeArmor);
      testHero.equip(wrongTypeArmor);
    });
  }

}
