public class Game {
    private Map currentMap;
    private WaveManager waveManager;
    private Player player;

    public Game(Map map, WaveManager waveManager, Player player) {
        this.currentMap = map;
        this.waveManager = waveManager;
        this.player = player;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public Player getPlayer() {
        return player;
    }

    public void setDifficulty(Difficulty difficulty) {
        if (difficulty == Difficulty.EASY) {
            player.setCoins(100);
            player.setHealth(100);
            waveManager = new WaveManager(7, 1);
        } else {
            if (difficulty == Difficulty.MEDIUM) {
                player.setCoins(300);
                player.setHealth(300);
                waveManager = new WaveManager(15, 1);
            }
        }
    }
}
