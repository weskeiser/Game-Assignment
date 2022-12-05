package Interfaces;

public interface Item {

  public default String inspect() {
    return "This item has no description. It appears the dev needs more coffee..";
  };

  public String getName();
}
