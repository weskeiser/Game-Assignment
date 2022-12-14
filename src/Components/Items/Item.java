package Components.Items;

public interface Item {

  default void inspect() {
    System.out.println("This item has no description. It appears the dev needs more coffee..");
  };

  String getName();

  void printName();

}
