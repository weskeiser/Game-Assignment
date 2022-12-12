## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

- Higher armor attribute provides a chance to deflect an attack of same type
- Maximum 15% chance per attribute per piece (Head, Torso, Legs)
- Different armor types have different base attributes that provide protection against attributes of same type
- Armor can also invoke special effects
- The task stipulates the getArmorAttributes method belongs to equipment manager. However as the equipment manager is implementd by game characters, I saw it redundant feature, since game characters already have a method to get total attributes and the method would have no practical use. The getArmorAttributes method is instead located at each individual armor type, where it logically belongs.

Combat:

1. Max hit of attacker is calculated
2. Whether defender deflected the attack is calculated
3. Actual hit is randomised
4. Defender takes damage
5. Attacker gains experience if not NPC
