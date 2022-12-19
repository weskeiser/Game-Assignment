package main.Game.Swing;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.GameAction.GameTask;
import main.Game.GameAction.Combat.CombatAction;
import main.Game.Swing.Sprites.Item;
import main.Game.Swing.Sprites.Player;

public class Board extends JPanel implements ActionListener {
  public static final int playerWidth = 28;
  public static final int playerHeight = 26;

  CombatAction combatTasks = new CombatAction.CombatTasksBuilder().build();
  private int boardX, boardY, boardWidth, boardHeight, playerX, playerY;

  int worldWidth = 1000;
  int worldHeight = 1000;

  private Camera cam;
  private Player player;
  private World world;

  private Timer timer = new Timer(15, this);

  private Hero hero;
  private Item item;
  // private PlayerStats playerStats;

  public Board(int boardX, int boardY, int boardW, int boardH, Hero hero) throws IOException {
    this.boardX = boardX;
    this.boardY = boardY;
    this.boardWidth = boardW;
    this.boardHeight = boardH;

    this.hero = hero;

    var gameTimer = new java.util.Timer();
    gameTimer.scheduleAtFixedRate(combatTasks, 0, GameTask.GAME_TICK_LENGTH);

    initGame();
  }

  public void initGame() throws IOException {

    player = new Player(hero, boardWidth, boardHeight, worldWidth, worldHeight);
    cam = new Camera(player, boardX, boardY, boardWidth, boardHeight, worldWidth, worldHeight);

    world = new World(cam, 0, 0, worldWidth, worldHeight);

    item = new Item(100, 100);

    addKeyListener(new TAdapter());
    setPreferredSize(new Dimension(boardHeight, boardWidth));
    setFocusable(true);
    setBackground(Color.BLACK);

    timer.start();

  }

  @Override
  protected void paintComponent(Graphics g) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("Helvetica,", Font.BOLD, 10));

    super.paintComponent(g);

    g.setColor(Color.ORANGE);
    world.paintComponent(g);

    int boundaryWidth = boardWidth / 2;
    int boundaryHeight = boardHeight / 2;

    var outOfCamBoundLeft = player.getX() <= boundaryWidth;
    var outOfCamBoundRight = player.getX() >= worldWidth - boundaryWidth;
    var outOfCamBoundTop = player.getY() <= boundaryHeight;
    var outOfCamBoundBottom = player.getY() >= worldHeight - boundaryHeight;

    playerX = outOfCamBoundLeft ? player.getX()
        : outOfCamBoundRight ? (player.getX() - boardWidth) : boundaryWidth;

    playerY = outOfCamBoundTop ? player.getY()
        : outOfCamBoundBottom ? (player.getY() - boardHeight) : boundaryHeight;

    g.drawImage(player.getImage(), playerX, playerY, this);

    g.drawImage(item.getImage(), item.getX() - cam.getX(), item.getY() - cam.getY(), this);

    g.drawRect(0, 0, boardWidth, boardHeight);

    g.setColor(Color.RED);

    g.drawString("[Char p: (" + (player.getX()) + "," + (player.getY()) + ")]",
        boardWidth / 2 - playerWidth * 2,
        boardHeight / 2 - playerHeight);

    g.drawString("[Peach p: (" + item.getX() + "," + item.getY() + ")]",
        item.getX() - cam.getX() - item.width * 2,
        item.getY() - cam.getY() - item.height);

    g.drawString("CamX:" + cam.getX() + ", CamY:" + cam.getY(), 200, 200);

    g.drawString("Distance: [" + (player.getX() - item.getX()) + "," +
        (player.getY() - item.getY()) + "]",
        item.getX() - 30 - cam.getX(), item.getY() + 30 - cam.getY());

    g.drawString("Dis player-edge of world:" + (player.getX() - world.getX()) +
        "," + (player.getY() - world.getY()),
        150, 150);

    // playerStats.paintComponent(g);

    Toolkit.getDefaultToolkit().sync();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    cam.move();
    player.move();

    checkCollisions();
    repaint();
  }

  private class TAdapter extends KeyAdapter {
    @Override
    public void keyReleased(KeyEvent e) {
      player.keyReleased(e);
      cam.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
      player.keyPressed(e);
      cam.keyPressed(e);
    }
  }

  public int getBoardWidth() {
    return boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  public void checkCollisions() {
    Rectangle r1 = player.getBounds();
    Rectangle r2 = item.getBounds();
    if (r1.intersects(r2)) {
      System.out.println(5);
      // hero.loseHealth(5);
    }
  }

}
