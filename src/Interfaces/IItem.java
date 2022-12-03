package Interfaces;

public interface IItem {

  public default String inspect() {
    return "This item has no name. It appears the dev needs more coffee..";
  };
}
