package Components.GameCharacters.Hero;

import java.util.EnumMap;

import Components.GameCharacters.Interfaces.CharacterAttribute;

public interface HeroInitiator {
  EnumMap<CharacterAttribute, Integer> init();
}
