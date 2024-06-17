import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameCanvas extends JPanel {
    private Game game;
    private String currentScreen;
    private Image easyMapImage;
    private Image mediumMapImage;
    private Image archerImage;
    private Image gladiatorImage;
    private Image bomberImage;
    private Image cannonImage;
    private Image fireMageImage;
    private Image catapultImage;
    private Image slugImage;
    private Image snailImage;
    private Image wormImage;
    private Image snakeImage;
    private Image bigSlugImage;
    private Image bigSnailImage;
    private Image bigWormImage;
    private Image bigSnakeImage;
    private Image blobfishImage;
    private Image enragedBlobfishImage;
    private String selectedTower;
    private Point selectedTowerPosition;
    private List<PlacedTower> placedTowers;
    private List<Enemy> enemies;
    private PlacedTower selectedPlacedTower;

    private JButton playButton;
    private JButton howToPlayButton;
    private JButton creditsButton;
    private JButton backButton;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton upgradeButton;

    private int countdown = 10;
    private Timer timer;
    private Timer waveTimer;

    private int currentWave = 0;
    private int totalWaves = 0;

    private final Point[] easyWaypoints = {
            new Point(200, 570),
            new Point(200, 220),
            new Point(600, 220),
            new Point(600, 830),
            new Point(1130, 830),
            new Point(1130, 500),
            new Point(2000, 500)
    };

    private final Point[] mediumWaypoints = {
            new Point(180, 500),
            new Point(500, 620),
            new Point(600, 510),
            new Point(420, 270),
            new Point(2000, 270)
    };

    public GameCanvas(Game game) {
        this.game = game;
        this.currentScreen = "MainMenu";
        this.placedTowers = new ArrayList<>();
        this.enemies = new ArrayList<>();
        setPreferredSize(new Dimension(1920, 1080));
        setLayout(null);

        // Load map images
        easyMapImage = loadImage("easy_map.png");
        mediumMapImage = loadImage("medium_map.png");

        // Load tower images
        archerImage = loadImage("archer.png");
        gladiatorImage = loadImage("gladiator.png");
        bomberImage = loadImage("bomber.png");
        cannonImage = loadImage("cannon.png");
        fireMageImage = loadImage("fireMage.png");
        catapultImage = loadImage("catapult.png");

        // Load enemy images
        slugImage = loadImage("slug.png");
        snailImage = loadImage("snail.png");
        wormImage = loadImage("worm.png");
        snakeImage = loadImage("snake.png");
        bigSlugImage = loadImage("big_slug.png");
        bigSnailImage = loadImage("big_snail.png");
        bigWormImage = loadImage("big_worm.png");
        bigSnakeImage = loadImage("big_snake.png");
        blobfishImage = loadImage("blobfish.png");
        enragedBlobfishImage = loadImage("enraged_blobfish.png");

        // Initialize buttons
        playButton = createButton("Play", 200);
        howToPlayButton = createButton("How to Play", 250);
        creditsButton = createButton("Credits", 300);
        backButton = createButton("Back", 850);
        easyButton = createButton("Easy", 200);
        mediumButton = createButton("Medium", 250);
        upgradeButton = createButton("Upgrade", getWidth() - 250);

        // Add action listeners
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCurrentScreen("MapSelection");
            }
        });
        howToPlayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCurrentScreen("HowToPlay");
            }
        });
        creditsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCurrentScreen("Credits");
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCurrentScreen("MainMenu");
            }
        });
        easyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setDifficulty(Difficulty.EASY);
                totalWaves = 6;
                setCurrentScreen("GameEasy");
                startCountdown();
            }
        });
        mediumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setDifficulty(Difficulty.MEDIUM);
                totalWaves = 10;
                setCurrentScreen("GameMedium");
                startCountdown();
            }
        });
        upgradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleUpgradeTower();
            }
        });

        add(playButton);
        add(howToPlayButton);
        add(creditsButton);
        add(backButton);
        add(easyButton);
        add(mediumButton);
        add(upgradeButton);

        backButton.setVisible(false);
        easyButton.setVisible(false);
        mediumButton.setVisible(false);
        upgradeButton.setVisible(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e);
            }
        });

        updateButtons();

        startEnemyMovement();
    }

    private JButton createButton(String text, int yPosition) {
        JButton button = new JButton(text);
        button.setBounds((getWidth() - 200) / 2, yPosition, 200, 50);
        return button;
    }

    public void setCurrentScreen(String screen) {
        this.currentScreen = screen;
        repaint();
        updateButtons();
    }

    private void updateButtons() {
        Dimension size = getSize();
        int centerX = (size.width - 200) / 2;

        playButton.setBounds(centerX, 200, 200, 50);
        howToPlayButton.setBounds(centerX, 250, 200, 50);
        creditsButton.setBounds(centerX, 300, 200, 50);
        backButton.setBounds(centerX, 850, 200, 50);
        easyButton.setBounds(centerX, 200, 200, 50);
        mediumButton.setBounds(centerX, 250, 200, 50);
        upgradeButton.setBounds(size.width - 310, size.height - 30, 300, 30);

        switch (currentScreen) {
            case "MainMenu":
                playButton.setVisible(true);
                howToPlayButton.setVisible(true);
                creditsButton.setVisible(true);
                backButton.setVisible(false);
                easyButton.setVisible(false);
                mediumButton.setVisible(false);
                upgradeButton.setVisible(false);
                break;
            case "MapSelection":
                playButton.setVisible(false);
                howToPlayButton.setVisible(false);
                creditsButton.setVisible(false);
                backButton.setVisible(true);
                easyButton.setVisible(true);
                mediumButton.setVisible(true);
                upgradeButton.setVisible(false);
                break;
            case "HowToPlay":
            case "Credits":
                playButton.setVisible(false);
                howToPlayButton.setVisible(false);
                creditsButton.setVisible(false);
                backButton.setVisible(true);
                easyButton.setVisible(false);
                mediumButton.setVisible(false);
                upgradeButton.setVisible(false);
                break;
            case "GameEasy":
            case "GameMedium":
                playButton.setVisible(false);
                howToPlayButton.setVisible(false);
                creditsButton.setVisible(false);
                backButton.setVisible(true);
                easyButton.setVisible(false);
                mediumButton.setVisible(false);
                upgradeButton.setVisible(selectedPlacedTower != null && selectedPlacedTower.tower.getLevel() < 4);
                break;
            default:
                playButton.setVisible(true);
                howToPlayButton.setVisible(true);
                creditsButton.setVisible(true);
                backButton.setVisible(false);
                easyButton.setVisible(false);
                mediumButton.setVisible(false);
                upgradeButton.setVisible(false);
                break;
        }
    }

    private Image loadImage(String path) {
        URL imgURL = getClass().getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    public void doLayout() {
        super.doLayout();
        updateButtons();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (currentScreen) {
            case "MainMenu":
                drawMainMenu(g);
                break;
            case "MapSelection":
                drawMapSelection(g);
                break;
            case "HowToPlay":
                drawHowToPlay(g);
                break;
            case "Credits":
                drawCredits(g);
                break;
            case "GameEasy":
                drawGameMap(g, easyMapImage);
                drawHUD(g);
                drawCountdown(g);
                drawTowerSelectionPanel(g);
                drawPlacedTowers(g);
                drawEnemies(g);
                drawTowerUpgradePanel(g);
                break;
            case "GameMedium":
                drawGameMap(g, mediumMapImage);
                drawHUD(g);
                drawCountdown(g);
                drawTowerSelectionPanel(g);
                drawPlacedTowers(g);
                drawEnemies(g);
                drawTowerUpgradePanel(g);
                break;
            default:
                drawMainMenu(g);
                break;
        }
    }

    private void drawMainMenu(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);

        Font titleFont = new Font("Arial", Font.BOLD, 36);
        g.setFont(titleFont);
        String title = "Welcome to Slimy Defense!";
        int titleWidth = g.getFontMetrics(titleFont).stringWidth(title);
        g.drawString(title, (getWidth() - titleWidth) / 2, 100);
    }

    private void drawMapSelection(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);

        Font titleFont = new Font("Arial", Font.BOLD, 36);
        g.setFont(titleFont);
        String title = "Select a map difficulty:";
        int titleWidth = g.getFontMetrics(titleFont).stringWidth(title);
        g.drawString(title, (getWidth() - titleWidth) / 2, 100);
    }

    private void drawHowToPlay(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);

        Font titleFont = new Font("Comic sans", Font.BOLD, 36);
        Font instructionFont = new Font("Comic sans", Font.PLAIN, 17);

        g.setFont(titleFont);
        String title = "How to Play Slimy Defense:";
        int titleWidth = g.getFontMetrics(titleFont).stringWidth(title);
        g.drawString(title, (getWidth() - titleWidth) / 2, 100);

        g.setFont(instructionFont);
        String[] instructions = {
                "1. Welcome to Slimy Defense, this is a tower defense game where you defend your 'base' from enemies such as slugs, snails, and more!",
                "2. Select the difficulty you want to play, easy or medium.",
                "3. You will be thrown into the game, and tasked with killing all enemies before they reach your base.",
                "4. Place and upgrade your towers with your starting cash during the 10-second countdown. Don't worry, you will make more cash later.",
                "5. You can place towers by selecting their image from the tower selection panel when in game, you can then click on where you want to place the tower on the map, keep in mind, you cannot place towers on spots such as water or the path.",
                "6. To upgrade towers, click on the tower and a different panel will show up, where a certain amount of cash is needed to upgrade the tower. When you upgrade a tower, it becomes stronger!",
                "7. Enemies will spawn from the 'spawn point', towers attack enemies within their range. When a tower depletes the enemy's health, the enemy dies and disappears.",
                "8. Earn money by passing waves to place more towers or upgrade existing ones. The waves will slowly get harder the farther you get into the game.",
                "9. Waves of enemies will spawn automatically, each wave will start 15 seconds after the previous wave.",
                "10. Prevent enemies from reaching your base to avoid losing health. If enemies pass and deplete all of your base's health, you lose!",
                "11. Survive all the waves to win the game!"
        };
        int yPosition = 200;
        for (String instruction : instructions) {
            int instructionWidth = g.getFontMetrics(instructionFont).stringWidth(instruction);
            g.drawString(instruction, (getWidth() - instructionWidth) / 2, yPosition);
            yPosition += 50;
        }
        String backInstruction = "Press Back to return to the main menu.";
        int backInstructionWidth = g.getFontMetrics(instructionFont).stringWidth(backInstruction);
        g.drawString(backInstruction, (getWidth() - backInstructionWidth) / 2, yPosition + 50);
    }

    private void drawCredits(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);

        Font titleFont = new Font("Arial", Font.BOLD, 36);
        g.setFont(titleFont);
        String title = "Game developed by Logan MacFarlane, Swhaib Sudaad, and Arman Popal.";
        int titleWidth = g.getFontMetrics(titleFont).stringWidth(title);
        g.drawString(title, (getWidth() - titleWidth) / 2, 100);

        Font creditFont = new Font("Arial", Font.PLAIN, 24);
        g.setFont(creditFont);
        String backInstruction = "Press Back to return to the main menu.";
        int instructionWidth = g.getFontMetrics(creditFont).stringWidth(backInstruction);
        g.drawString(backInstruction, (getWidth() - instructionWidth) / 2, 200);
    }

    private void drawGameMap(Graphics g, Image mapImage) {
        if (mapImage != null) {
            g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void drawHUD(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Health: " + game.getPlayer().getHealth(), 10, 20);
        g.drawString("Coins: " + game.getPlayer().getCoins(), 10, 50);
        g.drawString("Waves: " + currentWave + "/" + totalWaves, 10, 80);
    }

    private void drawCountdown(Graphics g) {
        if (countdown > 0) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game starts in: " + countdown, getWidth() / 2 - 150, getHeight() / 2);
        }
    }

    private void drawTowerSelectionPanel(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(10, getHeight() - 360, 250, 360);

        drawTowerSelection(g, archerImage, "Archer", 25, 30);
        drawTowerSelection(g, gladiatorImage, "Gladiator", 15, 90);
        drawTowerSelection(g, bomberImage, "Bomber", 40, 150);
        drawTowerSelection(g, cannonImage, "Cannon", 400, 210);
        drawTowerSelection(g, fireMageImage, "Fire Mage", 2500, 270);
        drawTowerSelection(g, catapultImage, "Catapult", 4000, 330);
    }

    private void drawTowerSelection(Graphics g, Image towerImage, String name, int cost, int yPosition) {
        g.setColor(Color.WHITE);
        g.drawRect(15, getHeight() - yPosition - 28, 40, 40);
        if (towerImage != null) {
            g.drawImage(towerImage, 16, getHeight() - yPosition - 27, 39, 39, this);
        }
        g.drawString(name + ": $" + cost, 70, getHeight() - yPosition);
    }

    private void drawPlacedTowers(Graphics g) {
        for (PlacedTower placedTower : placedTowers) {
            g.drawImage(placedTower.image, placedTower.position.x - 50, placedTower.position.y - 50, 140, 140, this);
        }
    }

    private void drawEnemies(Graphics g) {
        for (Enemy enemy : enemies) {
            g.drawImage(enemy.image, enemy.position.x, enemy.position.y, enemy.size.width, enemy.size.height, this);
        }
    }

    private void drawTowerUpgradePanel(Graphics g) {
        if (selectedPlacedTower != null) {
            Tower tower = selectedPlacedTower.tower;
            g.setColor(Color.BLACK);
            g.fillRect(getWidth() - 310, getHeight() - 310, 300, 300);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Tower Type: " + tower.getClass().getSimpleName(), getWidth() - 300, getHeight() - 280);
            g.drawString("Level: " + tower.getLevel(), getWidth() - 300, getHeight() - 260);
            g.drawString("Damage: " + tower.getDamage(), getWidth() - 300, getHeight() - 240);
            g.drawString("Fire Rate: " + tower.getFireRate(), getWidth() - 300, getHeight() - 220);
            g.drawString("Range: " + tower.getRange(), getWidth() - 300, getHeight() - 200);
            g.drawString("Splash AOE: " + tower.getSplashAOE(), getWidth() - 300, getHeight() - 180);
            g.drawString("Can Detect Hidden: " + tower.canDetectHidden(), getWidth() - 300, getHeight() - 160);
            if (tower.getLevel() < 4) {
                g.drawString("Upgrade Cost: $" + tower.getUpgradeCost(), getWidth() - 300, getHeight() - 140);
                upgradeButton.setVisible(true);
            } else {
                upgradeButton.setVisible(false);
            }
        } else {
            upgradeButton.setVisible(false);
        }
    }

    private void handleMousePressed(MouseEvent e) {
        if (currentScreen.equals("GameEasy") || currentScreen.equals("GameMedium")) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if (mouseX >= 15 && mouseX <= 55) {
                if (mouseY >= getHeight() - 30 - 40 && mouseY <= getHeight() - 30) {
                    selectedTower = "Archer";
                } else if (mouseY >= getHeight() - 90 - 40 && mouseY <= getHeight() - 90) {
                    selectedTower = "Gladiator";
                } else if (mouseY >= getHeight() - 150 - 40 && mouseY <= getHeight() - 150) {
                    selectedTower = "Bomber";
                } else if (mouseY >= getHeight() - 210 - 40 && mouseY <= getHeight() - 210) {
                    selectedTower = "Cannon";
                } else if (mouseY >= getHeight() - 270 - 40 && mouseY <= getHeight() - 270) {
                    selectedTower = "Fire Mage";
                } else if (mouseY >= getHeight() - 330 - 40 && mouseY <= getHeight() - 330) {
                    selectedTower = "Catapult";
                }
                selectedTowerPosition = e.getPoint();
                repaint();
            } else {
                for (PlacedTower placedTower : placedTowers) {
                    if (placedTower.contains(mouseX, mouseY)) {
                        selectedPlacedTower = placedTower;
                        repaint();
                        return;
                    }
                }

                if (selectedTower != null) {
                    selectedTowerPosition = e.getPoint();
                    Image towerImage = getTowerImage(selectedTower);
                    if (towerImage != null) {
                        int cost = getTowerCost(selectedTower);
                        if (game.getPlayer().getCoins() >= cost) {
                            game.getPlayer().subtractCoins(cost);
                            Tower tower = createTower(selectedTower);
                            assert tower != null;
                            tower.setLocation(new Location(selectedTowerPosition.x, selectedTowerPosition.y));
                            placedTowers.add(new PlacedTower(tower, towerImage, selectedTowerPosition));
                            selectedTower = null;
                            selectedTowerPosition = null;
                        }
                    }
                    repaint();
                }
            }
        }
    }

    private Image getTowerImage(String towerType) {
        switch (towerType) {
            case "Archer":
                return archerImage;
            case "Gladiator":
                return gladiatorImage;
            case "Bomber":
                return bomberImage;
            case "Cannon":
                return cannonImage;
            case "Fire Mage":
                return fireMageImage;
            case "Catapult":
                return catapultImage;
            default:
                return null;
        }
    }

    private int getTowerCost(String towerType) {
        switch (towerType) {
            case "Archer":
                return 25;
            case "Gladiator":
                return 15;
            case "Bomber":
                return 40;
            case "Cannon":
                return 400;
            case "Fire Mage":
                return 2500;
            case "Catapult":
                return 4000;
            default:
                return 0;
        }
    }

    private Tower createTower(String towerType) {
        switch (towerType) {
            case "Archer":
                return new Archer();
            case "Gladiator":
                return new Gladiator();
            case "Bomber":
                return new Bomber();
            case "Cannon":
                return new Cannon();
            case "Fire Mage":
                return new FireMage();
            case "Catapult":
                return new Catapult();
            default:
                return null;
        }
    }

    private void handleUpgradeTower() {
        if (selectedPlacedTower != null && selectedPlacedTower.tower != null) {
            Tower tower = selectedPlacedTower.tower;
            int upgradeCost = tower.getUpgradeCost();
            if (game.getPlayer().getCoins() >= upgradeCost) {
                game.getPlayer().subtractCoins(upgradeCost);
                tower.upgrade();
                repaint();
            }
        }
    }

    private void startCountdown() {
        countdown = 10;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (countdown > 0) {
                    countdown--;
                    repaint();
                } else {
                    timer.cancel();
                    currentWave = 0;
                    startEnemyWaves();
                }
            }
        }, 1000, 1000);
    }

    private void startEnemyWaves() {
        waveTimer = new Timer();
        waveTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentWave < totalWaves) {
                    currentWave++;
                    Point spawnPoint = new Point(0, 570);
                    Point[] waypoints = easyWaypoints;
                    if (currentScreen.equals("GameMedium")) {
                        spawnPoint = new Point(0, 500);
                        waypoints = mediumWaypoints;
                    }
                    generateEnemiesForWave(currentScreen, currentWave, spawnPoint, waypoints);
                    repaint();
                } else {
                    waveTimer.cancel();
                }
            }
        }, 0, 15000);
    }

    private void generateEnemiesForWave(String currentScreen, int waveNumber, Point spawnPoint, Point[] waypoints) {
        if (currentScreen.equals("GameEasy")) {
            if (waveNumber == 1) {
                for (int i = 0; i < 1; i++) {
                    enemies.add(new Slug(slugImage, spawnPoint, waypoints));
                }
            } else if (waveNumber == 2) {
                for (int i = 0; i < 2; i++) {
                    enemies.add(new Snail(snailImage, spawnPoint, waypoints));
                }
            } else if (waveNumber == 3) {
                for (int i = 0; i < 1; i++) {
                    enemies.add(new Snake(snakeImage, spawnPoint, waypoints));
                }
            } else if (waveNumber == 4) {
                for (int i = 0; i < 1; i++) {
                    enemies.add(new Snake(bigSlugImage, spawnPoint, waypoints));
                }
            } else if (waveNumber == 5) {
                enemies.add(new BigSlug(bigSnailImage, spawnPoint, waypoints));
                for (int i = 0; i < 1; i++) {
                    enemies.add(new Snake(snailImage, spawnPoint, waypoints));
                }
            } else if (waveNumber == 6) {
                enemies.add(new Blobfish(blobfishImage, spawnPoint, waypoints));
            }
        } else if (currentScreen.equals("GameMedium")) {
            if (waveNumber == 1) {
                for (int i = 0; i < 1; i++) {
                    enemies.add(new Snail(wormImage, spawnPoint, waypoints));
                }
            } else if (waveNumber == 2) {
                for (int i = 0; i < 1; i++) {
                    enemies.add(new Snail(snakeImage, spawnPoint, waypoints));
                }
            } else if (waveNumber == 3) {
                for (int i = 0; i < 2; i++) {
                    enemies.add(new Worm(wormImage, spawnPoint, waypoints));
                }
            } else if (waveNumber == 4) {
                enemies.add(new BigSnail(bigSnailImage, spawnPoint, waypoints));
            } else if (waveNumber == 5) {
                enemies.add(new BigSlug(bigSlugImage, spawnPoint, waypoints));
                for (int i = 0; i < 1; i++) {
                    enemies.add(new Snake(snakeImage, spawnPoint, waypoints));
                }
            } else if (waveNumber == 6) {
                enemies.add(new BigWorm(bigWormImage, spawnPoint, waypoints));
            } else if (waveNumber == 7) {
                enemies.add(new BigWorm(bigWormImage, spawnPoint, waypoints));
                enemies.add(new BigSlug(snailImage, spawnPoint, waypoints));
            } else if (waveNumber == 8) {
                    enemies.add(new BigWorm(bigSnakeImage, spawnPoint, waypoints));

            } else if (waveNumber == 9) {
                enemies.add(new BigSnake(bigSnakeImage, spawnPoint, waypoints));
                for (int i = 0; i < 2; i++) {
                    enemies.add(new Snake(snailImage, spawnPoint, waypoints));
                }
            } else if (waveNumber == 10) {
                enemies.add(new EnragedBlobfish(enragedBlobfishImage, spawnPoint, waypoints));
            }
        }
    }

    private void startEnemyMovement() {
        Timer moveTimer = new Timer();
        moveTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Enemy enemy : enemies) {
                    enemy.move();
                }
                repaint();
            }
        }, 0, 30);
    }

    private static class PlacedTower {
        private final Tower tower;
        private final Image image;
        private final Point position;

        public PlacedTower(Tower tower, Image image, Point position) {
            this.tower = tower;
            this.image = image;
            this.position = position;
        }

        public boolean contains(int x, int y) {
            Rectangle bounds = new Rectangle(position.x - 50, position.y - 50, 140, 140);
            return bounds.contains(x, y);
        }
    }

    private static class Enemy {
        protected final Image image;
        protected final Point position;
        protected final Point[] waypoints;
        protected int waypointIndex;
        protected Dimension size;
        protected double speed;

        public Enemy(Image image, Point position, Point[] waypoints, Dimension size, double speed) {
            this.image = image;
            this.position = position;
            this.waypoints = waypoints;
            this.waypointIndex = 0;
            this.size = size;
            this.speed = speed;
        }

        public void move() {
            if (waypointIndex < waypoints.length) {
                Point target = waypoints[waypointIndex];
                if (position.x < target.x) position.x += speed;
                if (position.x > target.x) position.x -= speed;
                if (position.y < target.y) position.y += speed;
                if (position.y > target.y) position.y -= speed;

                if (position.equals(target)) {
                    waypointIndex++;
                }
            }
        }
    }

    private static class Slug extends Enemy {
        public Slug(Image image, Point position, Point[] waypoints) {
            super(image, position, waypoints, new Dimension(100, 100), 2);
        }
    }

    private static class Snail extends Enemy {
        public Snail(Image image, Point position, Point[] waypoints) {
            super(image, position, waypoints, new Dimension(100, 100), 1.5);
        }
    }

    private static class Worm extends Enemy {
        public Worm(Image image, Point position, Point[] waypoints) {
            super(image, position, waypoints, new Dimension(100, 100), 2);
        }
    }

    private static class Snake extends Enemy {
        public Snake(Image image, Point position, Point[] waypoints) {
            super(image, position, waypoints, new Dimension(100, 100), 2.5);
        }
    }

    private static class BigSlug extends Enemy {
        public BigSlug(Image image, Point position, Point[] waypoints) {
            super(image, position, waypoints, new Dimension(150, 150), 2);
        }
    }

    private static class BigSnail extends Enemy {
        public BigSnail(Image image, Point position, Point[] waypoints) {
            super(image, position, waypoints, new Dimension(150, 150), 1.5);
        }
    }

    private static class BigWorm extends Enemy {
        public BigWorm(Image image, Point position, Point[] waypoints) {
            super(image, position, waypoints, new Dimension(150, 150), 2);
        }
    }

    private static class BigSnake extends Enemy {
        public BigSnake(Image image, Point position, Point[] waypoints) {
            super(image, position, waypoints, new Dimension(150, 150), 2.5);
        }
    }

    private static class Blobfish extends Enemy {
        public Blobfish(Image image, Point position, Point[] waypoints) {
            super(image, position, waypoints, new Dimension(200, 200), 1.25);
        }
    }

    private static class EnragedBlobfish extends Enemy {
        public EnragedBlobfish(Image image, Point position, Point[] waypoints) {
            super(image, position, waypoints, new Dimension(200, 200), 1.25);
        }
    }
}
