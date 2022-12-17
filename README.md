# Hero Game `Noroff Assignment #04`

---

## About The Game

A tick based game where the player handles a Hero. The player has the option of choosing a ranger, a rogue, a mage or a warrior as their character.

## Game Mechanics

#### Tick System

A game tick is `250ms`

The `gameTimer` is the global time keeper. Game actions are defined in extensions of `TimeTask` implementing extensions of the `GameTask` interface. Game tasks are scheduled and removed with methods on these instances and are ran every game tick.

#### Combat

The combat system has been designed with multiple algorithms.

A player can engage in combat with NPC Villains. If multiple Heroes are instantiated, they can also engage in combat with each other, as the game is designed with **multiplayer expansion in mind**.

A character can attack one enemy character at a time, but can be attacked by multiple enemy characters simultaneously.

If a character is attacked, it will automatically attack back if it is not already attacking someone else.

When a character performs an attack, they receive an **8 tick cooldown** time. Cooldown time will be **lower depending on their dexterity** level.

`Defense`
When an attack is performed the defender will first **attempt to deflect the attack**. The deflection chance depends on their worn armor and the attack type of the attacker. Each armor slot gives a maximum of 15% deflection chance, translating to a **maximum 45% deflection chance** given a level cap of 100 and no added bonuses.

When calculating the deflection chance, for each armor slot, **only the armorAttribute matching the attackAttribute of the attacker is taken into consideration**.

`Attack`
If the defender fails to deflect the attack, **the attacker's max hit is calculated**. The attacker's max hit depends on their **equipped weapon**, and their **strength attribute**. After the max hit has been calculated, the **actual hit is randomised**. If a roll is in the upper 25th percentile, there will be a re-roll. This contributes to making higher hits more special, and makes the game more unpredictable. The defender then takes the resulting hit, and the **attacker will receive experience equiavelent to the actual hit**, given they are not an NPC.

`Death`
When a character receives a final blow, their inventory and equipped items are dropped as loot, and becomes accessible, as Remains, to the player who defeated them.

---

---

---

- Higher armor attribute provides a chance to deflect an attack of same type
- Maximum 15% deflection chance per attribute per piece (Head, Torso, Legs)
- Different armor types have different base attributes that act as a multiplier in an algorithm with with armor level requirement to provide protection against attributes of same type when attacked
- Armor can also invoke special effects

Combat:

1. Whether defender deflected the attack is determined
2. Max hit of attacker is calculated
3. Damage is randomised\*
4. Defender takes damage
5. Attacker gains experience if not NPC

I have altered the implementation of some of the hard requirements under the assumption they will only draw points if they no longer implement the spirit of the requested feature or that they are a measurably worse implementation.

Some of the requirements from the default approach are negated by the specifications of the alternative approach (Appendix D, point 2).

---

Notes on modifications

Hero damage has contradictory specifications
In point 2) is the following specification:
• Strength – determines the physical strength of the character.
• Dexterity – determines the characters ability to attack with speed and nimbleness.
• Intelligence – determines the characters affinity with magic.
In point 5.2) is the following specification:
Each hero class has a damaging attribute which contributes to their total damage:
• Warrior – damage increased by total strength
• Mage – damage increased by total intelligence
• Ranger – damage increased by total dexterity
• Rogue – damage increased by total dexterity
To make a balanced combat system I have made base damage calculations depend only on the strength attribute out of the three, allowing the strength of the character to be determined by it.
The attack speed (tick penalty when performing an attack), relies on the dexterity attribute out of the three, allowing the characters' ability to attack with speed and nimbleness be determined by it.
Affinity with magic to be determined. Spells probably.

TotalAttributes have contradictory specifications.
In point 5.1) it says total attributes is the sum of levelling attributes and armor attributes.
In point 5.2) is a note stating damaging attribute can be calculated from TotalAttributes, implying total attributes contains, logically, all hero attributes.
I have went with the solution of totalAttributes, renamed defensiveAttributes, representing total worn armorAttributes, with a custom defenses calculation model laid out in \*\*\*\*. And leaving leveling attributes to their intuitive domain; representing the amount by which their attributes gain levels.

The task stipulates the getArmorAttributes method belongs to equipment manager. However as the equipment manager is implementd by game characters, I saw it redundant feature, since game characters already have a method to get total attributes and the method would have no practical use. The getArmorAttributes method is instead located at each individual armor type, where it in this implementation logically belongs.

---

Hard requirements:

- Heroes have attributes which increase by an amount dependent on herotype.
- Equipped items alter a hero's power, causing increased damage and defenses.
- Equipment is equippable depending on herotype.
- Custom exceptions

- Hero fields:
  • Name
  • Level - all heroes start at level 1
  • LevelAttributes - total from all levels
  \*\*\*\* renamed characterAttributes
  • Equipment - holds currently equipped items. <Slot, Item> datastructure. Initialized with null values in empty slots.
  \*\*\*\* renamed to equippedItems
  \*\*\*\* using an EnumMap, empty slots are automatically initialized with null values.
  • ValidWeaponTypes – a list of weapon types a hero can equip based on their subclass
  \*\*\*\* moved to HeroType
  • ValidArmorTypes - a list of armor types a hero can equip based on their subclass
  \*\*\*\* moved to HeroType

- Heroes have the following public facing methods:
  • Constructor – each hero is created by passing just a name.
  • LevelUp – level by 1 and levelAttributes by given specs
  • Equip – two variants, for equipping armor and weapons \*\*\*\*
  • Damage – calculated, not stored
  • TotalAttributes – calculated, not stored
  • Display – details of Hero to be displayed

- Hero
  Abstract Hero class to encapsulate all shared functionality. Methods with default implementation can be defined here to be overridden in child classes. If there is no default implementation for a method, make it abstract to be implemented in a child class.
  \*\*\*\* negated by specifications in alternative approach
  Interact with Hero class to satisfy Liscov SP.
  Heroes start with different attributes and level up at different rates as specified

- Display
  • Name
  • Class
  • Level
  • Total strength
  • Total dexterity
  • Total intelligence
  • Damage

- Damage
  Total damage = weaponDamage from equipped weapon + 1% for every point in damaging attribute
  If no weapon, weaponDamage is 1.
  • Hero damage = WeaponDamage \* (1 + DamagingAttribute/100)

- Hero Attributes
  • Strength – determines the physical strength of the character.
  • Dexterity – determines the characters ability to attack with speed and nimbleness.
  • Intelligence – determines the characters affinity with magic
  A class named HeroAttribute shall encapsulate these attributes and is the datatype for both LevelAttributes. Should have methods to add two instances together OR increase one instance by a specified amount.
  \*\*\*\* replaced with an enum named CharacterAttribute, since it applies to both Heroes and Villains. Methods to be determined...
  Should be able to display self.

- Total Attributes
  Sum of levelling attributes and combined armor attributes
  Total = LevelAttributes + (Sum of ArmorAttribute for all Armor in Equipment)
  Requires the equipment to be looped through with all the non-weapon slots being used in the calculation
  \*\*\*\* Ambiguous specification open to interpretation. Followed the wording of 'levelling attributes' + combined with armor attributes

- Equipment
  Weapon and Armor
  Parent Item abstract class
  \*\*\*\* replaced with Item interface
  All items share fields: Name, RequiredLevel, Slot
  \*\*\*\* RequiredLevel altered to be only required for equipment with the Equippable interface to allow for non level-gated items.
  Unequippable if required level not met.
  Each item have a designated equipment slot
  Slot is an enum with following values:
  • Weapon
  • Head
  • Body
  • Legs

- Weapons
  • Axes
  • Bows
  • Daggers
  • Hammers
  • Staffs
  • Swords
  • Wands

  Encapsulated in WeaponType enum and composed into Weapon class
  Weapons have a weapon type and deal damage.
  Damage is represented as a WeaponDamage field (Located in WeaponItem enum)
  When a weapon is created it is automatically given slot of Weapon

- Armor
  • Cloth
  • Leather
  • Mail
  • Plate

  Encapsulated in ArmorType enum and composed into Armor class
  Has attributes providing bonuses when equipped of type HeroAttribute and should be called ArmorAttribute
  \*\*\*\* HeroAttribute renamed to CharacterAttribute
  \*\*\*\* ArmorAttributes split into baseArmorAttributes dependent on and residing in ArmorType enum, and armorAttributes which resides in each ArmorType enum (ArmorItem implementers) after being calculated based on armor item level requirement.

Certain hero classes can equip certain weapon types:
• Mages – Staff, Wand
• Rangers – Bow
• Rogues – Dagger, Sword
• Warriors – Axe, Hammer, Sword
Custom InvalidWeaponException thrown with appropriate message if wrong type or insufficient level
\*\*\*\* Split into individual
Certain hero classes can equip certain armor types:
• Mages – Cloth
• Rangers – Leather, Mail
• Rogues – Leather, Mail
• Warriors – Mail, Plate
Custom InvalidArmorException thrown with appropriate message if wrong type or insufficient level

- Alternative approach
  A single Hero class
  Strategy pattern for attributes
  Factory or builder pattern for Hero creation. Can do the same for Weapon and Armor
  Implement EquipmentManager interface composed into Hero. Manages EquippedWeapons and EquippedArmor. Should have public methods for equipping weapons and armor, as well as getting the total armor attributes

- Public GitLab repository containing all the code and a well formatted README with appropriate commit messages and branches.
