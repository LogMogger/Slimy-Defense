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
        switch (difficulty) {
            case EASY:
                player.setCoins(100000);
                player.setHealth(100);
                waveManager = new WaveManager(7, 1.0f);
                break;
            case MEDIUM:
                player.setCoins(100000);
                player.setHealth(100);
                waveManager = new WaveManager(15, 0.8f);
                break;
        }
    }
}
