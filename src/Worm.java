public class Worm extends Enemy {

    public Worm(int health, double speed, int coinsOnKill, boolean isHidden, Path path) {
        super(health, speed, coinsOnKill, isHidden, path);
    }

    @Override
    public void move() {
        currentPosition.setX(currentPosition.getX() + (int)speed);

    }

    @Override
    public void attack(Base base) {
        base.decreaseHealth(3);

    }

    @Override
    public void render() {
    }
}
