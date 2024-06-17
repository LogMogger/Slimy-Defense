public class MainMenu {
    public void displayMenu() {
        System.out.println("Welcome to Slimy Defense!");
        System.out.println("1. Play");
        System.out.println("2. How to Play");
        System.out.println("3. Credits");
    }

    public void handlePlayButton() {
        System.out.println("Starting the game...");
    }

    public void handleHowToPlayButton() {
        HowToPlayScreen howToPlayScreen = new HowToPlayScreen();
        howToPlayScreen.displayInstructions();
    }

    public void handleCreditsButton() {
        System.out.println("Game developed by Logan, Arman, Swhaib");
    }
}
