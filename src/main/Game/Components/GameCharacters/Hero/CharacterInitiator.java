package main.Game.Components.GameCharacters.Hero;

import java.util.EnumMap;

import main.Game.Components.GameCharacters.Interfaces.CharacterAttribute;

public interface CharacterInitiator {
  EnumMap<CharacterAttribute, Integer> init();
}
