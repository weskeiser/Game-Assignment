package Tests.GameCharacters.Hero;

import static org.junit.Assert.assertEquals;

import java.util.EnumMap;
import java.util.Map;

import org.junit.*;

import Game.Components.GameCharacters.Hero.Hero;
import Game.Components.GameCharacters.Hero.HeroType;
import Game.Components.GameCharacters.Interfaces.CharacterAttribute;
import Game.Components.Items.Equipment.Armor.Armor;
import Game.Components.Items.Equipment.Armor.ArmorItems.Mail;

public class DefensiveAttributesTest {
  Hero warrior = new Hero.HeroBuilder("Hero TestName", HeroType.WARRIOR).build();

  EnumMap<CharacterAttribute, Integer> defensiveAttributes = new EnumMap<>(CharacterAttribute.class);

  //

  @Before
  public void levelHeroUp() {
    // Level hero up to lvl22 for equipment requirements
    for (int i = 0; i < 22; i++) {
      warrior.levelUp();
    }
  }

  @After
  public void resetVariables() {
    // Reset hero after each test
    warrior = new Hero.HeroBuilder("Hero TestName", HeroType.WARRIOR).build();

    // Reset defensiveAttributes after each test
    defensiveAttributes = new EnumMap<>(CharacterAttribute.class);
  }

  //

  @Test
  public void NoArmorEquipped_GetDefensiveAttributes_DefensiveAttributesCorrect() {
    defensiveAttributes = warrior.getDefensiveAttributes();

    assertEquals(createAttributeMap(0, 0, 0), defensiveAttributes);
  }

  @Test
  public void OneArmorItemEquipped_GetDefensiveAttributes_DefensiveAttributesCorrect() {

    try {
      warrior.addToInventory(new Armor.ArmorBuilder(Mail.ROYAL_MAIL).build());
      warrior.equip(1);

      defensiveAttributes = warrior.getDefensiveAttributes();
    } catch (Throwable err) {
    }

    EnumMap<CharacterAttribute, Integer> expectedDefensiveAttributes = createAttributeMap(2, 3, 1);

    assertEquals(expectedDefensiveAttributes, defensiveAttributes);
  }

  @Test
  public void TwoArmorItemsEquipped_GetDefensiveAttributes_DefensiveAttributesCorrect() {

    try {
      warrior.addToInventory(new Armor.ArmorBuilder(Mail.ROYAL_MAIL).build());
      warrior.addToInventory(new Armor.ArmorBuilder(Mail.STEEL_CHAIN_HOOD).build());
      warrior.equip(2);
      warrior.equip(1);

      defensiveAttributes = warrior.getDefensiveAttributes();
    } catch (Throwable err) {
    }

    EnumMap<CharacterAttribute, Integer> expectedDefensiveAttributes = createAttributeMap(3, 5, 1);

    assertEquals(expectedDefensiveAttributes, defensiveAttributes);
  }

  @Test
  public void OneArmorItemEquipped_ItemIsReplaced_DefensiveAttributesCorrect() {

    try {
      warrior.addToInventory(new Armor.ArmorBuilder(Mail.HOLY_CHAIN_HOOD).build());
      warrior.equip(1);

      warrior.addToInventory(new Armor.ArmorBuilder(Mail.STEEL_CHAIN_HOOD).build());
      warrior.equip(1);

      defensiveAttributes = warrior.getDefensiveAttributes();
    } catch (Throwable err) {
    }

    EnumMap<CharacterAttribute, Integer> expectedDefensiveAttributes = createAttributeMap(1, 2, 0);
    warrior.display();

    assertEquals(expectedDefensiveAttributes, defensiveAttributes);
  }

  public EnumMap<CharacterAttribute, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    return new EnumMap<>(Map.ofEntries(
        Map.entry(CharacterAttribute.STRENGTH, strength),
        Map.entry(CharacterAttribute.DEXTERITY, dexterity),
        Map.entry(CharacterAttribute.INTELLIGENCE, intelligence)));
  }

}
