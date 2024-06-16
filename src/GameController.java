public class GameController {
    private GameState currentState;
    private InputHandler inputHandler;

    public GameController() {
        this.inputHandler = new InputHandler();
        this.currentState = GameState.MAIN_MENU;
    }

    public void startGame() {
        currentState = GameState.PLAYING;
        System.out.println("Game is now playing.");
    }

    public void pauseGame() {
        currentState = GameState.PAUSED;
        System.out.println("Game is now paused.");
    }

    public void resumeGame() {
        currentState = GameState.PLAYING;
        System.out.println("Game is now playing.");
    }

    public void endGame() {
        currentState = GameState.GAME_OVER;
        System.out.println("Game is over.");
    }
}
