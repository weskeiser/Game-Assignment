package main;

import java.awt.EventQueue;
import java.io.IOException;

import main.Game.Game;

public class Main {

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      Game game = null;

      try {
        game = new Game();
      } catch (IOException e) {
        e.printStackTrace();
      }

      game.setVisible(true);
    });
  }
}
