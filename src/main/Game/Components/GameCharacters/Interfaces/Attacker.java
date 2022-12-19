package main.Game.Components.GameCharacters.Interfaces;

import java.util.Optional;

import main.Game.Components.GameCharacters.Remains.Remains;
import main.Game.Components.Items.Equipment.EquipmentManager;
import main.Game.Components.Items.Equipment.Weapon.Weapon;

public interface Attacker extends Combatant {
  CharacterAttribute getAttackAttribute();

  int getAttackCooldown();

  void gainExperience(double expGain);

  void receiveLootAccess(Remains remains);

  void setAttackCooldown(int ticks);

  boolean decrementIfAttackCooldown();

  int getAttackSpeed();

  double getMaxHit();

  default double getMaxHit(Attacker attacker) {

    Optional<Weapon> equippedWeapon = ((EquipmentManager) attacker).getEquippedWeapon();

    double weaponDamage = equippedWeapon.map(Weapon::getDamageMultiplier).orElse(1.0);

    int strengthAttribute = ((AttributeManager) attacker).getCharacterAttributes().get(CharacterAttribute.STRENGTH);

    return weaponDamage * (1 + strengthAttribute / 100);
  }

}
