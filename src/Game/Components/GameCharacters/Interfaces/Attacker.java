package Game.Components.GameCharacters.Interfaces;

import Game.Components.Exceptions.InvalidEquipmentException;
import Game.Components.GameCharacters.Remains.Remains;
import Game.Components.Items.Equipment.EquipmentManager;
import Game.Components.Items.Equipment.Weapon.Weapon;

public interface Attacker {
  public CharacterAttribute getDamagingAttribute();

  int getAttackCooldown();

  void gainExperience(double expGain);

  void receiveLootAccess(Remains remains);

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

    return weaponDamage * (1 + strengthAttribute / 100);
  }

}
