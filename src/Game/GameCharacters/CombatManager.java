package Game.GameCharacters;

import Game.Exceptions.InvalidEquipmentException;
import Game.Items.Equipment.Weapon.Weapon;

public interface CombatManager {

  void defend(double maxHit, GameCharacter foe);

  default double getMaxHit(GameCharacter gameCharacter) {
    Weapon equippedWeapon = null;

    try {
      Weapon fetched = gameCharacter.getEquippedWeapon();
      equippedWeapon = fetched;
    } catch (InvalidEquipmentException err) {
      // Expected behaviour, no handling needed.
    }

    CharacterAttribute damagingAttribute = gameCharacter.getCharacterType().getDamagingAttribute();
    int attributeDamage = gameCharacter.getHeroAttributes().get(damagingAttribute);

    double weaponDamage = (equippedWeapon != null) ? equippedWeapon.getDamageMultiplier() : 1;

    System.out.println("Calculated damage: " + weaponDamage * (1 + attributeDamage / 100));
    return weaponDamage * (1 + attributeDamage / 100);
  }

}