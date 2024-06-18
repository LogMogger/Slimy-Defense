public class GameController {
    private GameState State;
    private InputHandler inputHandler;

    public GameController() {
        this.inputHandler = new InputHandler();
        this.State = GameState.MAIN_MENU;
    }

    public void startGame() {
        State = GameState.PLAYING;
    }

    public void pauseGame() {
        State = GameState.PAUSED;
    }

    public void resumeGame() {
        State = GameState.PLAYING;
    }

    public void endGame() {
        State = GameState.GAME_OVER;
    }
}
