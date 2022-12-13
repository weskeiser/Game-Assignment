package Game.GameCharacters.Hero;

import java.util.EnumMap;

import Game.GameCharacters.Interfaces.CharacterAttribute;

public interface HeroInitiator {
  EnumMap<CharacterAttribute, Integer> init();
}
