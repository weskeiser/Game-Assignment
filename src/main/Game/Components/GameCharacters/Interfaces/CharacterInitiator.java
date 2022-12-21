package main.Game.Components.GameCharacters.Interfaces;

import java.util.EnumMap;

public interface CharacterInitiator {
  EnumMap<CharacterAttribute, Integer> init();
}
