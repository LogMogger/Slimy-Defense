public class Worm extends Enemy {

    public Worm(int health, double speed, int coinsOnKill, boolean isHidden, Path path) {
        super(health, speed, coinsOnKill, isHidden, path);
    }

    @Override
    public void move() {
        // Implement the movement logic specific to Worm
        currentPosition.setX(currentPosition.getX() + (int)speed);
        System.out.println("Worm moves with speed: " + this.speed);
    }

    @Override
    public void attack(Base base) {
        // Implement the attack logic specific to Worm
        base.decreaseHealth(3); // Example: Decrease base health by 3
        System.out.println("Worm attacks.");
    }

    @Override
    public void render() {
        // Logic to render the Worm
        System.out.println("Rendering Worm at position: " + currentPosition);
    }
}
