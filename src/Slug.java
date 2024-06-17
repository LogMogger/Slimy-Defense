public class Slug extends Enemy {

    public Slug(int health, double speed, int coinsOnKill, boolean isHidden, Path path) {
        super(health, speed, coinsOnKill, isHidden, path);
    }

    @Override
    public void move() {
        currentPosition.setX(currentPosition.getX() + (int)speed);
        System.out.println("Slug moves with speed: " + this.speed);
    }

    @Override
    public void attack(Base base) {
        base.decreaseHealth(health);
        System.out.println("Slug attacks.");
    }

    @Override
    public void render() {
        System.out.println("Rendering Slug at position: " + currentPosition);
    }
}
