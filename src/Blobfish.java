public class Blobfish extends Enemy {

    public Blobfish(int health, double speed, int coinsOnKill, boolean isHidden, Path path) {
        super(health, speed, coinsOnKill, isHidden, path);
    }

    @Override
    public void move() {
        currentPosition.setX(currentPosition.getX() + (int)speed);
        System.out.println("Blobfish moves with speed: " + this.speed);
    }

    @Override
    public void attack(Base base) {
        base.decreaseHealth(5);
        System.out.println("Blobfish attacks.");
    }

    @Override
    public void render() {
        System.out.println("Rendering Blobfish at position: " + currentPosition);
    }
}
