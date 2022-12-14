package Game.Components.GameCharacters.Hero;

import java.util.EnumMap;

import Game.Components.GameCharacters.Interfaces.CharacterAttribute;

public interface HeroInitiator {
  EnumMap<CharacterAttribute, Integer> init();
}
