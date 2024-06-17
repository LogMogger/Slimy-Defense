import java.util.ArrayList;
import java.util.List;

public class Player {
    private int health;
    private int coins;
    private List<Tower> towers;

    public Player(int health, int coins) {
        this.health = health;
        this.coins = coins;
        this.towers = new ArrayList<>();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int amount) {
        this.coins += amount;
    }

    public void subtractCoins(int amount) {
        if (this.coins >= amount) {
            this.coins -= amount;
        } else {
            System.out.println("Not enough coins!");
        }
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void removeTower(Tower tower) {
        towers.remove(tower);
    }
}
