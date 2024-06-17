public class Worm extends Enemy {

    public Worm(int health, double speed, int coinsOnKill, boolean isHidden, Path path) {
        super(health, speed, coinsOnKill, isHidden, path);
    }

    @Override
    public void move() {
        currentPosition.setX(currentPosition.getX() + (int)speed);
        System.out.println("Worm moves with speed: " + this.speed);
    }

    @Override
    public void attack(Base base) {
        base.decreaseHealth(3);
        System.out.println("Worm attacks.");
    }

    @Override
    public void render() {
        System.out.println("Rendering Worm at position: " + currentPosition);
    }
}
