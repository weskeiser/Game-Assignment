package Game.GameCharacters.Interfaces;

import Game.Exceptions.InvalidEquipmentException;
import Game.Items.Equipment.EquipmentManager;
import Game.Items.Equipment.Weapon.Weapon;

public interface Attacker {
  double getMaxHit();

  default double getMaxHit(Attacker attacker) {

    double weaponDamage = 1;

    try {

      Weapon equippedWeapon = ((EquipmentManager) attacker).getEquippedWeapon();

      weaponDamage = equippedWeapon.getDamageMultiplier();
    } catch (InvalidEquipmentException err) {
      // Expected behaviour, no handling needed.
    }

    // Damaging attribute is character dependent
    CharacterAttribute damagingAttribute = ((GameCharacter) attacker).getCharacterType().getDamagingAttribute();

    // Set attributeDamage according to damagingAttribute
    int attributeDamage = ((AttributeManager) attacker).getHeroAttributes().get(damagingAttribute);

    System.out.println("Calculated damage: " + weaponDamage * (1 + attributeDamage / 100));
    return weaponDamage * (1 + attributeDamage / 100);
  }

}
