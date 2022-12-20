package main.Game.Swing;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.Timer;

import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.GameCharacters.Villain.Villain;
import main.Game.GameAction.GameTask;
import main.Game.GameAction.Combat.CombatAction;
import main.Game.GameController.GameController;
import main.Game.Swing.Sprites.Item;

public class Board extends JPanel implements ActionListener {
  public static final int playerWidth = 28;
  public static final int playerHeight = 26;

  private int boardX, boardY, boardWidth, boardHeight, playerX, playerY;

  int worldWidth = 1920;
  int worldHeight = 1080;
  private Item item;
  private Avatar heroAvatar;
  private World world;

  private HeroStats heroStats;
  private Hero hero;
  private HashSet<Villain> villains;

  int backgroundX;
  int backgroundY;

  private Timer timer = new Timer(10, this);

  GameController gameController = new GameController();
  CombatAction combatTasks = new CombatAction.CombatTasksBuilder().build();
  java.util.Timer gameTimer = new java.util.Timer();

  @Override
  protected void paintComponent(Graphics g) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("Helvetica,", Font.BOLD, 25));

    super.paintComponent(g);

    g.setColor(Color.ORANGE);
    world.paintComponent(g);

    playerX = heroAvatar.getSpriteX();
    playerY = heroAvatar.getSpriteY();

    g.drawImage(heroAvatar.getImage(), playerX, playerY, this);

    g.drawImage(item.getImage(), item.getX() - heroAvatar.getCamX(), item.getY() - heroAvatar.getCamY(), this);

    g.drawRect(0, 0, boardWidth, boardHeight);

    g.setColor(Color.WHITE);

    g.drawString("[spriteX: (" + (heroAvatar.getSpriteX()) + ", spriteY:" +
        (heroAvatar.getSpriteY()) + ")]",
        boardWidth / 2 - playerWidth * 2,
        boardHeight / 2 - playerHeight);

    g.drawString("[Peach p: (" + item.getX() + "," + item.getY() + ")]",
        item.getX() - heroAvatar.getCamX() - item.width * 2,
        item.getY() - heroAvatar.getCamY() - item.height);

    g.drawString("CamX:" + heroAvatar.getCamX() + ", CamY:" +
        heroAvatar.getCamY(), 200, 200);

    g.drawString("Distance: [" + (heroAvatar.getSpriteX() - item.getX()) + "," +
        (heroAvatar.getSpriteY() - item.getY()) + "]",
        item.getX() - 30 - heroAvatar.getCamX(), item.getY() + 30 -
            heroAvatar.getCamY());

    g.drawString("Dis heroAvatar-edge of world:" + (heroAvatar.getSpriteX() -
        world.getX()) +
        "," + (heroAvatar.getSpriteY() - world.getY()),
        150, 150);

    heroStats.paintComponent(g);

    Toolkit.getDefaultToolkit().sync();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    heroAvatar.move();

    checkCollisions();
    repaint();
  }

  private class TAdapter extends KeyAdapter {
    @Override
    public void keyReleased(KeyEvent e) {
      heroAvatar.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
      heroAvatar.keyPressed(e);
    }
  }

  public int getBoardWidth() {
    return boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  public void checkCollisions() {
    Rectangle r1 = heroAvatar.getBounds();
    Rectangle r2 = item.getBounds();
    if (r1.intersects(r2)) {
      System.out.println(5);
    }
  }

  private Board(BoardBuilder builder) throws IOException {
    this.boardX = builder.boardX;
    this.boardY = builder.boardY;
    this.boardWidth = builder.boardWidth;
    this.boardHeight = builder.boardHeight;

    this.backgroundX = builder.backgroundX;
    this.backgroundY = builder.backgroundY;

    this.hero = builder.hero;
    this.villains = builder.villains;

    this.heroStats = new HeroStats(builder.hero);
    this.heroAvatar = new Avatar(hero, boardX, boardY, boardWidth, boardHeight, worldWidth, worldHeight);
    this.world = new World(heroAvatar, 0, 0, worldWidth, worldHeight, builder.backgroundImgPath);
    this.item = new Item(100, 100);

    addKeyListener(new TAdapter());
    setPreferredSize(new Dimension(boardHeight, boardWidth));
    setFocusable(true);
    setBackground(Color.BLACK);

    timer.start();
    gameTimer.scheduleAtFixedRate(combatTasks, 0, GameTask.GAME_TICK_LENGTH);
  }

  public static class BoardBuilder {
    private int boardX;
    private int boardY;
    private int boardWidth;
    private int boardHeight;

    String backgroundImgPath;
    int backgroundX;
    int backgroundY;

    private HashSet<Villain> villains;
    private Hero hero;

    public BoardBuilder setVillains(HashSet<Villain> villains) {
      this.villains = villains;

      return this;
    }

    public BoardBuilder setBackground(String path, int backgroundX, int backgroundY) {
      this.backgroundImgPath = path;
      this.backgroundX = backgroundX;
      this.backgroundY = backgroundY;

      return this;
    }

    public BoardBuilder setDimensions(int boardX, int boardY, int boardWidth, int boardHeight) {
      this.boardX = boardX;
      this.boardY = boardY;
      this.boardWidth = boardWidth;
      this.boardHeight = boardHeight;

      return this;
    }

    public BoardBuilder(Hero hero) {
      this.hero = hero;

      this.boardX = 0;
      this.boardY = 0;
      this.boardWidth = 960;
      this.boardHeight = 540;
    }

    public Board build() throws IOException {
      return new Board(this);
    }
  }

}
