package Tests.Components.GameCharacters.Hero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import Game.Components.GameCharacters.Hero.Hero;
import Game.Components.GameCharacters.Hero.HeroType;
import Game.Components.GameCharacters.Interfaces.AttributeManager;
import Game.Components.GameCharacters.Interfaces.CharacterAttribute;
import Game.Components.Items.Equipment.Armor.Armor;
import Game.Components.Items.Equipment.Armor.ArmorItem;
import Game.Components.Items.Equipment.Armor.ArmorItems.Cloth;
import Game.Components.Items.Equipment.Armor.ArmorItems.Mail;

@RunWith(Parameterized.class)

public class DefensiveAttributesTest {
  private Hero testHero;

  Armor expectedBodyArmorOne;

  Armor expectedHeadArmorOne;
  Armor expectedHeadArmorTwo;

  // Params for constructor
  @Parameterized.Parameters
  public static Collection<Object[]> testParams() {

    return Arrays.asList(
        new Object[][] {
            { HeroType.WARRIOR, Mail.ROYAL_MAIL, Mail.STEEL_CHAIN_HOOD, Mail.HOLY_CHAIN_HOOD },
            { HeroType.ROGUE, Mail.ROYAL_MAIL, Mail.STEEL_CHAIN_HOOD, Mail.HOLY_CHAIN_HOOD },
            { HeroType.RANGER, Mail.ROYAL_MAIL, Mail.STEEL_CHAIN_HOOD, Mail.HOLY_CHAIN_HOOD },
            { HeroType.MAGE, Cloth.IRONED_ROBE_TOP, Cloth.STIFF_WIZARD_HAT, Cloth.INVISIBLE_WIZARD_HAT } });
  }

  // Constructor
  public DefensiveAttributesTest(HeroType heroType, ArmorItem expectedBodyArmorOne, ArmorItem expectedHeadArmorOne,
      ArmorItem expectedHeadArmorTwo) {

    this.testHero = new Hero.HeroBuilder("TestHero Name", heroType).build();

    this.expectedBodyArmorOne = new Armor.ArmorBuilder(expectedBodyArmorOne).build();

    this.expectedHeadArmorOne = new Armor.ArmorBuilder(expectedHeadArmorOne).build();
    this.expectedHeadArmorTwo = new Armor.ArmorBuilder(expectedHeadArmorTwo).build();

    // Level up before each test
    for (int i = 0; i < 22; i++) {
      testHero.levelUp();
    }
  }

  @Test

  public void NoArmorEquipped_GetDefensiveAttributes_DefensiveAttributesCorrect() {

    assertEquals(AttributeManager.newAttributeMap(0, 0, 0), testHero.getDefensiveAttributes());
  }

  @Test

  public void OneArmorItemEquipped_GetDefensiveAttributes_DefensiveAttributesCorrect() {

    try {
      testHero.addToInventory(expectedBodyArmorOne);
      testHero.equip(expectedBodyArmorOne);

    } catch (Throwable err) {
      fail();
    }

    EnumMap<CharacterAttribute, Integer> expectedDefensiveAttributes = expectedBodyArmorOne.getArmorAttributes();

    assertEquals(expectedDefensiveAttributes, testHero.getDefensiveAttributes());
  }

  @Test

  public void TwoArmorItemsEquipped_GetDefensiveAttributes_DefensiveAttributesCorrect() {

    try {
      testHero.addToInventory(expectedBodyArmorOne);
      testHero.equip(expectedBodyArmorOne);

      testHero.addToInventory(expectedHeadArmorOne);
      testHero.equip(expectedHeadArmorOne);

    } catch (Throwable err) {
      fail();
    }

    EnumMap<CharacterAttribute, Integer> expectedDefensiveAttributes = new EnumMap<>(CharacterAttribute.class);

    EnumMap<CharacterAttribute, Integer> bodyArmorAttributes = expectedBodyArmorOne.getArmorAttributes();
    EnumMap<CharacterAttribute, Integer> headArmorAttributes = expectedHeadArmorOne.getArmorAttributes();

    for (CharacterAttribute cAttribute : bodyArmorAttributes.keySet()) {
      expectedDefensiveAttributes.put(cAttribute,
          bodyArmorAttributes.get(cAttribute) + headArmorAttributes.get(cAttribute));
    }

    assertEquals(expectedDefensiveAttributes, testHero.getDefensiveAttributes());
  }

  @Test

  public void OneArmorItemEquipped_ItemIsReplaced_DefensiveAttributesCorrect() {

    try {
      testHero.addToInventory(expectedHeadArmorOne);
      testHero.equip(expectedHeadArmorOne);

      testHero.addToInventory(expectedHeadArmorTwo);
      testHero.equip(expectedHeadArmorTwo);

    } catch (Throwable err) {
      fail();
    }

    EnumMap<CharacterAttribute, Integer> expectedDefensiveAttributes = expectedHeadArmorTwo.getArmorAttributes();

    assertEquals(expectedDefensiveAttributes, testHero.getDefensiveAttributes());
  }
}
