package main.Game.Swing.Sprites;

public class Item extends Sprite {
  public String name = "Peach";

  public Item(int x, int y) {
    super(x, y);
    initSprite();
  }

  private void initSprite() {
    loadImage("src/lib/img/sprites/Peach.png");
    getImageDimensions();
  }
}
