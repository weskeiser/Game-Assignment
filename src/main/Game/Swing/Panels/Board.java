package main.Game.Swing.Panels;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Optional;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import main.Game.Components.GameCharacters.Hero.Hero;
import main.Game.Components.GameCharacters.Interfaces.Defender;
import main.Game.Components.GameCharacters.Villain.Villain;
import main.Game.GameAction.GameTask;
import main.Game.GameAction.Combat.CombatAction;

public class Board extends JPanel implements ActionListener {

  private int worldWidth, worldHeight;
  private int boardX, boardY, boardWidth, boardHeight;

  private World world;

  private Hero hero;
  private HeroAvatar heroAvatar;
  private ProfileBar heroProfile;

  private Villain villain;
  private VillainAvatar villainAvatar;
  private EnemyProfile enemyProfile;

  HealthBars healthBars;
  InventoryPanel inventoryPanel;
  EquipmentPanel equipmentPanel;

  CombatAction combatTasks = new CombatAction.CombatTasksBuilder().build();;

  private Timer timer = new Timer(10, this);
  private java.util.Timer combatTimer = new java.util.Timer();

  // ~ Painter

  @Override
  protected void paintComponent(Graphics g) {
    g.setFont(new Font("Helvetica,", Font.BOLD, 25));

    super.paintComponent(g);

    world.paintComponent(g);

    g.drawImage(heroAvatar.getImage(), heroAvatar.getSpriteX(), heroAvatar.getSpriteY(), this);

    g.drawImage(villainAvatar.getImage(), villainAvatar.getSpriteX() - heroAvatar.getCamX(),
        villainAvatar.getSpriteY() - heroAvatar.getCamY(), this);

    if (inventoryPanel != null) {
      inventoryPanel.paintComponent(g);
    }

    if (equipmentPanel != null) {
      equipmentPanel.paintComponent(g);
    }

    if (enemyProfile != null) {
      enemyProfile.paintComponent(g);
    }

    heroProfile.paintComponent(g);

    healthBars.paintComponent(g);

    //

    g.setColor(Color.WHITE);

    // x and y coordinates overlay

    // g.drawString("[spriteX: (" + (heroAvatar.getSpriteX()) + ", spriteY:" +
    // (heroAvatar.getSpriteY()) + ")]",
    // boardWidth / 2,
    // boardHeight / 4);

    // g.drawString("camX:" + heroAvatar.getCamX() + ", camY:" +
    // heroAvatar.getCamY(), 100, boardHeight / 4);

    Toolkit.getDefaultToolkit().sync();
  }

  // Actions

  @Override
  public void actionPerformed(ActionEvent e) {
    heroAvatar.move();
    villainAvatar.move();

    // Disengage attack if attacking
    if (!hero.getCurrentlyAttacking().isEmpty()) {

      if (checkIfWithinDistance(220, villainAvatar)) {
        combatTasks.disengageAttacker(hero);
        combatTasks.disengageAttacker(villain);

        healthBars.removeAvatar(heroAvatar);
        healthBars.removeAvatar(villainAvatar);

        villainAvatar.startRoaming();

        enemyProfile = null;
      }
    }

    repaint();
  }

  private boolean checkIfWithinDistance(int distance, VillainAvatar villainAvatar) {
    int villainXleft = villainAvatar.getSpriteX();
    int villainWidth = villainAvatar.getWidth();
    int villainYleft = villainAvatar.getSpriteY();
    int villainHeight = villainAvatar.getHeight();

    int distanceX = heroAvatar.getXDistanceFromTarget(villainXleft - villainWidth + 80);
    int distanceY = heroAvatar.getYDistanceFromTarget(villainYleft - villainHeight);

    return distanceX > distance || distanceX < -distance || distanceY > distance || distanceY < -distance;

  }

  // Clicks

  private class mouseAdapter extends MouseInputAdapter {
    @Override
    public void mousePressed(MouseEvent e) {

      var heroCamX = heroAvatar.getCamX();
      var heroCamY = heroAvatar.getCamY();

      var villainBounds = villainAvatar.getRelativeBounds(heroCamX, heroCamY);

      var mouseX = e.getX();
      var mouseY = e.getY();

      // Attack villain
      if (villainBounds.contains(mouseX, mouseY) && !checkIfWithinDistance(220, villainAvatar)) {

        Optional<Defender> heroCurrentlyAttacking = hero.getCurrentlyAttacking();

        heroCurrentlyAttacking.ifPresentOrElse((defender) -> {
          hero.performSpell(defender);
        }, () -> {
          combatTasks.newAttack(hero, villain);

          healthBars.addAvatar(heroAvatar);
          healthBars.addAvatar(villainAvatar);

          villainAvatar.stopRoaming();

          enemyProfile = new EnemyProfile(villain, boardWidth);
        });

        if (heroCurrentlyAttacking.isEmpty()) {
          combatTasks.newAttack(hero, villain);

          healthBars.addAvatar(heroAvatar);
          healthBars.addAvatar(villainAvatar);

          villainAvatar.stopRoaming();

          enemyProfile = new EnemyProfile(villain, boardWidth);
        }
      }

      // Profile photo

      if (heroProfile.getProfilePhotoPosition().contains(mouseX, mouseY)) {
        toggleInventory();
        toggleEquipmentPanel();
      }
    }
  }

  private void toggleEquipmentPanel() {
    equipmentPanel = equipmentPanel == null ? equipmentPanel = new EquipmentPanel(hero, boardWidth, boardHeight) : null;
  }

  private void toggleInventory() {
    inventoryPanel = inventoryPanel == null ? inventoryPanel = new InventoryPanel(hero, boardWidth, boardHeight) : null;
  }

  public int getBoardWidth() {
    return boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  private Board(BoardBuilder builder) throws IOException {
    this.boardX = builder.boardX;
    this.boardY = builder.boardY;
    this.boardWidth = builder.boardWidth;
    this.boardHeight = builder.boardHeight;

    this.worldWidth = builder.worldWidth;
    this.worldHeight = builder.worldHeight;

    this.villain = builder.villain;
    this.hero = builder.hero;

    this.heroProfile = new ProfileBar(builder.hero, boardWidth);
    this.heroAvatar = new HeroAvatar(hero, boardX, boardY, boardWidth, boardHeight, worldWidth, worldHeight);
    this.world = new World(heroAvatar, 0, 0, worldWidth, worldHeight, builder.backgroundImgPath);

    this.villain = builder.villain;
    this.villainAvatar = new VillainAvatar(builder.villain, boardX, boardY, boardWidth, boardHeight);

    healthBars = new HealthBars(heroAvatar);

    addKeyListener(heroAvatar);
    addMouseListener(new mouseAdapter());

    setPreferredSize(new Dimension(boardHeight, boardWidth));
    setFocusable(true);

    combatTimer.scheduleAtFixedRate(combatTasks, 0, GameTask.GAME_TICK_LENGTH);
    timer.start();
  }

  public static class BoardBuilder {
    private int boardX;
    private int boardY;
    private int boardWidth;
    private int boardHeight;

    private Hero hero;
    private Villain villain;

    String backgroundImgPath;
    int worldWidth;
    int worldHeight;

    public BoardBuilder setVillain(Villain villain) {
      this.villain = villain;

      return this;
    }

    public BoardBuilder setBackground(String path, int worldWidth, int worldHeight) {
      this.backgroundImgPath = path;
      this.worldHeight = worldHeight;
      this.worldHeight = worldHeight;

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
