package Game.GameCharacters.Interfaces;

import Game.Exceptions.InvalidEquipmentException;
import Game.GameCharacters.Remains.Remains;
import Game.Items.Equipment.EquipmentManager;
import Game.Items.Equipment.Weapon.Weapon;

public interface Attacker {
  void gainExperience(double expGain);

  void addRemains(Remains remains);

  void setAttackCooldown(int ticks);

  boolean decrementIfAttackCooldown();

  int getAttackSpeed();

  double getMaxHit();

  default double getMaxHit(Attacker attacker) {

    double weaponDamage = 1;

    try {

      Weapon equippedWeapon = ((EquipmentManager) attacker).getEquippedWeapon();

      weaponDamage = equippedWeapon.getDamageMultiplier();
    } catch (InvalidEquipmentException err) {
      // Expected behaviour, no handling needed.
    }

    int strengthAttribute = ((AttributeManager) attacker).getCharacterAttributes().get(CharacterAttribute.STRENGTH);

    System.out.println("Calculated maxHit: " + weaponDamage * (1 +
        strengthAttribute / 100));
    return weaponDamage * (1 + strengthAttribute / 100);
  }

}
