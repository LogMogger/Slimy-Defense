import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WaveManager {
    private List<Wave> waves;
    private int currentWaveIndex;
    private int totalWaves;
    private double spawnInterval;
    private Timer waveTimer;
    private boolean waveInProgress;

    public WaveManager(int totalWaves, double spawnInterval) {
        this.waves = new ArrayList<>();
        this.currentWaveIndex = 0;
        this.totalWaves = totalWaves;
        this.spawnInterval = spawnInterval;
        this.waveInProgress = false;

        // Initialize waves (example, you can customize this as needed)
        for (int i = 0; i < totalWaves; i++) {
            waves.add(new Wave(generateEnemiesForWave(i + 1)));
        }
    }

    private List<Enemy> generateEnemiesForWave(int waveNumber) {
        List<Enemy> enemies = new ArrayList<>();
        // Example: Add a slug for each waveNumber (customize this logic as needed)
        for (int i = 0; i < waveNumber; i++) {
            enemies.add(new Slug(10, 1.0, 5, false, new Path(generatePath())));
        }
        return enemies;
    }

    private List<Location> generatePath() {
        List<Location> path = new ArrayList<>();
        // Example path (customize this as needed)
        path.add(new Location(0, 250));
        path.add(new Location(300, 250));
        path.add(new Location(300, 450));
        path.add(new Location(600, 450));
        path.add(new Location(600, 250));
        path.add(new Location(900, 250));
        return path;
    }

    public void startWave() {
        if (currentWaveIndex < waves.size()) {
            waveInProgress = true;
            Wave currentWave = waves.get(currentWaveIndex);
            waveTimer = new Timer();
            waveTimer.scheduleAtFixedRate(new TimerTask() {
                private int enemyIndex = 0;

                @Override
                public void run() {
                    if (enemyIndex < currentWave.getEnemies().size()) {
                        Enemy enemy = currentWave.getEnemies().get(enemyIndex);
                        // Spawn the enemy (you need to implement the logic to handle the enemy in the game)
                        spawnEnemy(enemy);
                        enemyIndex++;
                    } else {
                        waveTimer.cancel();
                        waveInProgress = false;
                        checkWaveCompletion();
                    }
                }
            }, 0, (int) (spawnInterval * 1000));
        }
    }

    public void checkWaveCompletion() {
        if (!waveInProgress) {
            Wave currentWave = waves.get(currentWaveIndex);
            boolean allEnemiesDefeated = true;
            for (Enemy enemy : currentWave.getEnemies()) {
                if (enemy.isAlive()) {
                    allEnemiesDefeated = false;
                    break;
                }
            }
            if (allEnemiesDefeated) {
                currentWaveIndex++;
                if (currentWaveIndex < totalWaves) {
                    startWave();
                } else {
                    // All waves completed (you can add your game completion logic here)
                    System.out.println("All waves completed!");
                }
            }
        }
    }

    private void spawnEnemy(Enemy enemy) {
        // Logic to add the enemy to the game and start its movement (to be implemented)
        System.out.println("Spawning enemy at position: " + enemy.getCurrentPosition());
    }

    public int getCurrentWaveIndex() {
        return currentWaveIndex;
    }

    public int getTotalWaves() {
        return totalWaves;
    }

    public List<Wave> getWaves() {
        return waves;
    }
}
