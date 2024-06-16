public class HowToPlayScreen {
    public void displayInstructions() {
        System.out.println("How to Play Slimy Defense:");
        System.out.println("1. The game begins with a 10-second countdown during which you can place any towers you want using your starting cash.");
        System.out.println("2. Click on a tower icon at the bottom of the screen and place it on the grid. Towers cannot be placed on the enemy path.");
        System.out.println("3. As waves of enemies approach, they will follow the path towards your base.");
        System.out.println("4. Your towers will automatically attack enemies within their range.");
        System.out.println("5. Defeat all enemies before the next wave starts to earn more money.");
        System.out.println("6. Use the earned money to place more towers or upgrade existing ones.");
        System.out.println("7. If any enemies reach your base, they will take away health points equal to their remaining health.");
        System.out.println("8. If the base health reaches zero, the game will end, and you lose.");
        System.out.println("9. The goal is to survive as many waves as possible by strategically placing and upgrading towers.");
        System.out.println("Press Back to return to the main menu.");
    }

    public void handleBackButton() {
        System.out.println("Returning to main menu...");
    }
}
