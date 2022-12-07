package Game.GameCharacters.Actions;

import Game.GameCharacters.CharacterAttribute;
import Game.GameCharacters.GameCharacter;
import Game.Items.Equipment.Weapon.Weapon;

public interface CombatManager {

  void defend(double maxHit, GameCharacter foe);

  default double getMaxHit(GameCharacter gameCharacter) {
    Weapon equippedWeapon = null;

    try {
      Weapon fetched = gameCharacter.getEquippedWeapon();
      equippedWeapon = fetched;
    } catch (Throwable err) {
      System.out.println(err.getMessage());
    }

    CharacterAttribute damagingAttribute = gameCharacter.getCharacterType().getDamagingAttribute();
    int attributeDamage = gameCharacter.getHeroAttributes().get(damagingAttribute);

    double weaponDamage = (equippedWeapon != null) ? equippedWeapon.getDamageMultiplier() : 1;

    System.out.println("Calculated damage: " + weaponDamage * (1 + attributeDamage / 100));
    return weaponDamage * (1 + attributeDamage / 100);

  }

}