# Hero Game

`Noroff Assignment #04`

---

## About The Game

A tick based fantasy game with Heroes and Villains. The player plays as a Hero and has the option of choosing a ranger, a rogue, a mage or a warrior as their character. They can equip weapons and armor, manage their items, and engage in combat.

---

## Table of Contents

- [About The Code](https://github.com/weskeiser/Game-Assignment#about-the-code)
- [Game Mechanics](https://github.com/weskeiser/Game-Assignment#game-mechanics)
  - [Tick System](https://github.com/weskeiser/Game-Assignment#tick-system)
  - [Combat](https://github.com/weskeiser/Game-Assignment#combat)
    - [Defense](https://github.com/weskeiser/Game-Assignment#defense)
    - [Attack](https://github.com/weskeiser/Game-Assignment#attack)
    - [Death](https://github.com/weskeiser/Game-Assignment#death)
- [Modifications](https://github.com/weskeiser/Game-Assignment#modifications)
  - [Hero Damage](https://github.com/weskeiser/Game-Assignment#hero-damage)
  - [Total Attributes](https://github.com/weskeiser/Game-Assignment#total-attributes)
  - [Hero Attributes](https://github.com/weskeiser/Game-Assignment#hero-attributes)

---

## About The Code

The alternative approach outlined in Appendix D, point 2 has been chosen for this task. The game has also been slightly expanded on.

As required in the alternative approach outline the same functionality is still present. However with a few reasoned [modifications](https://github.com/weskeiser/Game-Assignment#task-modifications).

The last couple of days of the assignment I decided to rush making a GUI for the game. It is unfinished and I have not had time to document it. It is however very amusing, and you can try it by running a debug session.

Move around: WASD keys.
Open equipment and inventory: Click the profile picture in the bottom right.
Attack: Click on the opponent.
Disengage: Run away from the opponent.

---

## Game Mechanics

### Tick System

The `gameTimer` is the global time keeper. Game actions are defined in extensions of `TimeTask` implementing extensions of the `GameTask` interface. Game tasks are scheduled and removed with methods on these instances and are ran every game tick.

A game tick is `250ms`

### Combat

The combat system has been designed with multiple algorithms.

A player can engage in combat with NPC `Villains`. A `Hero` can also engage in combat with another, as the game is designed with **multiplayer expansion in mind**.

A character can attack one enemy character at a time, but can be attacked by multiple enemy characters simultaneously.

If not attacking someone else, a character will attack back automatically.

When a character performs an attack, they receive `attackCooldown`. Base cooldown time is **8 ticks**, with an amount (`attackSpeed`) subtracted from it, depending on their `dexterity` level.

#### Defense

When an attack is performed the defender will first **attempt to deflect the attack**.

The `deflectionChance` depends on the worn armor of the `Defender`, and the attack type of the `Attacker`.

Each armortype has a base attribute configuration that when calculated with the level requirement yields a deflection chance. Each armor slot gives a maximum of 15% `deflectionChance`, translating to a **maximum 45%** `deflectionChance` given a level cap of 100 and no added bonuses.

When calculating the `deflectionChance`, for each armor slot, only the `armorAttribute` matching the `attackAttribute` of the attacker is taken into consideration.

#### Attack

If the `Defender` fails to deflect the attack, the `maxHit` of the `Attacker` is calculated.

The `maxHit` depends on the `equippedWeapon` and `strengthAttribute` of the `Attacker`.

After it has been calculated, the `actualHit` is **randomised**. If a roll is in the upper 25th percentile, there will be a re-roll. This contributes to making higher hits more special, and makes the game more unpredictable.Lost in Translation

The `Defender` then takes the resulting hit, and the `Attacker` receives `experience` equivalent to the `actualHit`, given they are not an NPC.

#### Death

When a character receives a `finalBlow`, their `inventory` and `equippedItems` are surrendered and becomes accessible as `Remains` to the player who defeated them. All related combat engagements are removed from the combat tasks.

---

## Modifications

Listed below are features deviating from the specifications.

### Hero Damage

#### Specification in Appendix A, point 2.0:

> • Strength – determines the physical strength of the character.
> • Dexterity – determines the characters ability to attack with speed and nimbleness.
> • Intelligence – determines the characters affinity with magic.

##### Specification in Appendix B, point 5.2:

> The damage a hero deals is based in their currently equipped weapon and increased by their attributes. Each hero class has a damaging attribute which contributes to their total damage:
>
> • Warrior – damage increased by total strength
> • Mage – damage increased by total intelligence
> • Ranger – damage increased by total dexterity
> • Rogue – damage increased by total dexterity
>
> NOTE: Damaging attribute does not need to be stored, it can just be calculated from TotalAttributes

If both specifications were to be taken into consideration, `Warrior` would have little benefits from dexterity and intelligence, while also having the least boost in damage from the damaging attribute.

##### To make a balanced combat system I have made some alterations:

Base damage calculations depend on the `STRENGTH` attribute, allowing the strength of the character to be determined by it.

Attack speed relies on the `DEXTERITY` attribute (tick penalty when performing an attack), allowing the characters' ability to attack with speed and nimbleness be determined by it.

Summoning a familiar relies on the `INTELLIGENCE` attribute., allowing the affinity with magic be determined by it.

### Total Attributes

TotalAttributes has been renamed `defensiveAttributes`, representing the total of worn `armorAttributes`, with a custom defenses calculation model laid out in \*\*\*\*. `levelingAttributes` have been left to their intuitive domain; representing the amount by which a Hero's attributes gain levels, and do not contribute to total attributes.

Appendix D, point 2 stipulates the `EquipmentManager` should have a `getArmorAttributes` method. I have moved it to each armor item, where it logically fits in my implementation.

### Hero Attributes

HeroAttributes renamed `CharacterAttributes`.

##### Specification in Appendix B, point 5.2:

> This class should have methods to add two instances together and return the sum OR increase the one instance by a specified amount. Which to use is up to you on what best suits your solution.

I see no use for such methods and have not implemented them.

---
