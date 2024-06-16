public class GUIManager {
    private HUD hud;
    private TowerPanel towerPanel;
    private TowerSelectionPanel towerSelectionPanel;
    private MainMenu mainMenu;
    private MapSelectionScreen mapSelectionScreen;
    private CreditsScreen creditsScreen;
    private HowToPlayScreen howToPlayScreen;

    public GUIManager() {
        this.hud = new HUD();
        this.towerPanel = new TowerPanel();
        this.towerSelectionPanel = new TowerSelectionPanel();
        this.mainMenu = new MainMenu();
        this.mapSelectionScreen = new MapSelectionScreen();
        this.creditsScreen = new CreditsScreen();
        this.howToPlayScreen = new HowToPlayScreen();
    }

    public void showMenu(String menuName) {
        System.out.println("Showing menu: " + menuName);
    }

    public void hideMenu(String menuName) {
        System.out.println("Hiding menu: " + menuName);
    }

    public void updateHUD(Player player) {
        hud.updateHUD(player);
    }

    public void openTowerPanel(Tower tower) {
        towerPanel.updatePanel(tower);
    }

    public void updateTowerSelectionPanel() {
        towerSelectionPanel.displayTowerOptions();
    }

    public void displayMainMenu() {
        mainMenu.displayMenu();
    }

    public void displayMapSelectionScreen() {
        mapSelectionScreen.displayMapSelection();
    }

    public void displayHowToPlayScreen() {
        howToPlayScreen.displayInstructions();
    }

    public void displayCreditsScreen() {
        creditsScreen.displayCredits();
    }
}
