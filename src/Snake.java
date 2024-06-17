public class Snake extends Enemy {

    public Snake(int health, double speed, int coinsOnKill, boolean isHidden, Path path) {
        super(health, speed, coinsOnKill, isHidden, path);
    }

    @Override
    public void move() {
        currentPosition.setX(currentPosition.getX() + (int)speed);
        System.out.println("Snake moves with speed: " + this.speed);
    }

    @Override
    public void attack(Base base) {
        base.decreaseHealth(4);
        System.out.println("Snake attacks.");
    }

    @Override
    public void render() {
        System.out.println("Rendering Snake at position: " + currentPosition);
    }
}
