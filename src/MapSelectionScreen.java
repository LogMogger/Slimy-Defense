public class MapSelectionScreen {
    public void displayMapSelection() {
        System.out.println("Select a map difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.println("4. Back");
    }

    public void handleEasyButton() {
        System.out.println("Easy map selected.");
    }

    public void handleMediumButton() {
        System.out.println("Medium map selected.");
    }

    public void handleHardButton() {
        System.out.println("Hard map selected.");
    }

    public void handleBackButton() {
        System.out.println("Returning to main menu...");
    }
}
