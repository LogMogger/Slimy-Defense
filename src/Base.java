public class Base {
    private int health;

    public Base(int health) {
        this.health = health;
    }

    public void decreaseHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }

    public int getHealth() {
        return health;
    }
}
