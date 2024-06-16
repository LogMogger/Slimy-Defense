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
    private String selectedTower;
    private Point selectedTowerPosition;
    private List<PlacedTower> placedTowers;

    private JButton playButton;
    private JButton howToPlayButton;
    private JButton creditsButton;
    private JButton backButton;
    private JButton easyButton;
    private JButton mediumButton;

    private int countdown = 10;
    private Timer timer;

    public GameCanvas(Game game) {
        this.game = game;
        this.currentScreen = "MainMenu"; // Start with the main menu
        this.placedTowers = new ArrayList<>();
        setPreferredSize(new Dimension(1920, 1080));
        setLayout(null);

        // Load map images
        easyMapImage = loadImage("easy_map.png");
        mediumMapImage = loadImage("medium_map.png");

        // Load tower images
        archerImage = loadImage("archer.png");
        gladiatorImage = loadImage("gladiator.jpeg");
        bomberImage = loadImage("bomber.png");
        cannonImage = loadImage("cannon.jpg");
        fireMageImage = loadImage("fireMage.jpg");
        catapultImage = loadImage("catapult.png");

        // Initialize buttons
        playButton = createButton("Play", 200);
        howToPlayButton = createButton("How to Play", 250);
        creditsButton = createButton("Credits", 300);
        backButton = createButton("Back", 850);
        easyButton = createButton("Easy", 200);
        mediumButton = createButton("Medium", 250);

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
                // Set the game difficulty to Easy and display the easy map
                game.setDifficulty(Difficulty.EASY);
                setCurrentScreen("GameEasy");
                startCountdown();
            }
        });
        mediumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Set the game difficulty to Medium and display the medium map
                game.setDifficulty(Difficulty.MEDIUM);
                setCurrentScreen("GameMedium");
                startCountdown();
            }
        });

        // Add buttons to panel
        add(playButton);
        add(howToPlayButton);
        add(creditsButton);
        add(backButton);
        add(easyButton);
        add(mediumButton);

        // Hide back button initially
        backButton.setVisible(false);
        easyButton.setVisible(false);
        mediumButton.setVisible(false);

        // Add mouse listener for tower placement
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e);
            }
        });

        // Update button positions initially
        updateButtons();
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

        switch (currentScreen) {
            case "MainMenu":
                playButton.setVisible(true);
                howToPlayButton.setVisible(true);
                creditsButton.setVisible(true);
                backButton.setVisible(false);
                easyButton.setVisible(false);
                mediumButton.setVisible(false);
                break;
            case "MapSelection":
                playButton.setVisible(false);
                howToPlayButton.setVisible(false);
                creditsButton.setVisible(false);
                backButton.setVisible(true);
                easyButton.setVisible(true);
                mediumButton.setVisible(true);
                break;
            case "HowToPlay":
            case "Credits":
                playButton.setVisible(false);
                howToPlayButton.setVisible(false);
                creditsButton.setVisible(false);
                backButton.setVisible(true);
                easyButton.setVisible(false);
                mediumButton.setVisible(false);
                break;
            case "GameEasy":
            case "GameMedium":
                playButton.setVisible(false);
                howToPlayButton.setVisible(false);
                creditsButton.setVisible(false);
                backButton.setVisible(true);
                easyButton.setVisible(false);
                mediumButton.setVisible(false);
                break;
            default:
                playButton.setVisible(true);
                howToPlayButton.setVisible(true);
                creditsButton.setVisible(true);
                backButton.setVisible(false);
                easyButton.setVisible(false);
                mediumButton.setVisible(false);
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
        updateButtons(); // Ensure buttons are positioned correctly when layout changes
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
                drawHUD(g); // Draw the HUD on top of the map
                drawCountdown(g);
                drawTowerSelectionPanel(g); // Draw the tower selection panel
                drawPlacedTowers(g); // Draw placed towers
                break;
            case "GameMedium":
                drawGameMap(g, mediumMapImage);
                drawHUD(g); // Draw the HUD on top of the map
                drawCountdown(g);
                drawTowerSelectionPanel(g); // Draw the tower selection panel
                drawPlacedTowers(g); // Draw placed towers
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
        Font instructionFont = new Font("Comic sans", Font.PLAIN, 18);

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
                "5. You can place towers by selecting them from the tower selection panel when in game, you can then click on where you want to place the tower on the map, keep in mind, you cannot place towers on spots such as water or the path.",
                "6. To upgrade towers, click on the tower and a different panel will show up, where a certain amount of cash is needed to upgrade the tower. When you upgrade a tower, it becomes stronger!",
                "7. Enemies will spawn from the 'spawn point', towers attack enemies within their range. When a tower depletes the enemy's health, the enemy dies and disappears.",
                "8. Earn money by passing waves to place more towers or upgrade existing ones. The waves will slowly get harder the farther you get into the game.",
                "9. Waves of enemies will spawn automatically, each wave will start after the last one had all it of its enemies gone.",
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
        // Draw HUD elements such as coins and health
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Health: " + game.getPlayer().getHealth(), 10, 20);
        g.drawString("Coins: " + game.getPlayer().getCoins(), 10, 50);
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
            g.drawImage(placedTower.image, placedTower.position.x - 50, placedTower.position.y - 50, 100, 100, this);
        }
    }

    private void handleMousePressed(MouseEvent e) {
        if (currentScreen.equals("GameEasy") || currentScreen.equals("GameMedium")) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            // Check if a tower button is clicked
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
                // Place the tower on the map
                if (selectedTower != null) {
                    selectedTowerPosition = e.getPoint();
                    Image towerImage = getTowerImage(selectedTower);
                    if (towerImage != null) {
                        int cost = getTowerCost(selectedTower);
                        if (game.getPlayer().getCoins() >= cost) {
                            game.getPlayer().subtractCoins(cost);
                            placedTowers.add(new PlacedTower(towerImage, selectedTowerPosition));
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
                    // Nothing happens after the countdown ends
                }
            }
        }, 1000, 1000);
    }

    private static class PlacedTower {
        private final Image image;
        private final Point position;

        public PlacedTower(Image image, Point position) {
            this.image = image;
            this.position = position;
        }
    }
}
