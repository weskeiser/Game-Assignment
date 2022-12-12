package Tests.GameCharacters.Hero;

import static org.junit.Assert.assertEquals;

import java.util.EnumMap;
import java.util.Map;

import org.junit.*;

import Game.GameCharacters.CharacterAttribute;
import Game.GameCharacters.Hero.Hero;
import Game.GameCharacters.Hero.HeroType;
import Game.Items.Equipment.Armor.Armor;
import Game.Items.Equipment.Armor.ArmorItems.Mail;

public class TotalAttributesTest {
  private final EnumMap<CharacterAttribute, Integer> WARRIOR_STARTING_ATTRIBUTES = createAttributeMap(3, 2, 1);

  Hero warrior = new Hero.HeroBuilder("Hero TestName", HeroType.WARRIOR).build();

  EnumMap<CharacterAttribute, Integer> totalAttributes = new EnumMap<>(CharacterAttribute.class);

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

    // Reset totalAttributes after each test
    totalAttributes = new EnumMap<>(CharacterAttribute.class);
  }

  //

  @Test
  public void NoArmorEquipped_GetTotalAttributes_TotalAttributesCorrect() {
    totalAttributes = warrior.getTotalAttributes();

    assertEquals(WARRIOR_STARTING_ATTRIBUTES, totalAttributes);
  }

  @Test
  public void OneArmorItemEquipped_GetTotalAttributes_TotalAttributesCorrect() {

    try {
      warrior.addToInventory(new Armor.ArmorBuilder(Mail.ROYAL_MAIL).build());
      warrior.equip(1);

      totalAttributes = warrior.getTotalAttributes();
    } catch (Throwable err) {
    }

    // Royal mail armorAttributes: 2,3,1
    // Warrior levelingAttributes: 3,2,1
    // Total: 5,5,2
    EnumMap<CharacterAttribute, Integer> expectedTotalAttributes = createAttributeMap(5, 5, 2);

    assertEquals(expectedTotalAttributes, totalAttributes);
  }

  @Test
  public void TwoArmorItemsEquipped_GetTotalAttributes_TotalAttributesCorrect() {

    try {
      warrior.addToInventory(new Armor.ArmorBuilder(Mail.ROYAL_MAIL).build());
      warrior.addToInventory(new Armor.ArmorBuilder(Mail.STEEL_CHAIN_HOOD).build());
      warrior.equip(2);
      warrior.equip(1);

      totalAttributes = warrior.getTotalAttributes();
    } catch (Throwable err) {
    }

    // Steel chain hood armorAttributes: 1,2,0
    // Royal mail armorAttributes: 2,3,1
    // Warrior levelingAttributes: 3,2,1
    // Total: 6,7,2
    EnumMap<CharacterAttribute, Integer> expectedTotalAttributes = createAttributeMap(6, 7, 2);

    assertEquals(expectedTotalAttributes, totalAttributes);
  }

  @Test
  public void OneArmorItemEquipped_ItemIsReplaced_TotalAttributesCorrect() {

    try {
      warrior.addToInventory(new Armor.ArmorBuilder(Mail.HOLY_CHAIN_HOOD).build());
      warrior.equip(1);

      warrior.addToInventory(new Armor.ArmorBuilder(Mail.STEEL_CHAIN_HOOD).build());
      warrior.equip(1);

      totalAttributes = warrior.getTotalAttributes();
    } catch (Throwable err) {
    }

    // Steel chain hood armorAttributes: 1,2,0
    // Warrior levelingAttributes: 3,2,1
    // Total: 4,4,1
    EnumMap<CharacterAttribute, Integer> expectedTotalAttributes = createAttributeMap(4, 4, 1);
    warrior.display();

    assertEquals(expectedTotalAttributes, totalAttributes);
  }

  public EnumMap<CharacterAttribute, Integer> createAttributeMap(int strength, int dexterity, int intelligence) {
    return new EnumMap<>(Map.ofEntries(
        Map.entry(CharacterAttribute.STRENGTH, strength),
        Map.entry(CharacterAttribute.DEXTERITY, dexterity),
        Map.entry(CharacterAttribute.INTELLIGENCE, intelligence)));
  }

}
