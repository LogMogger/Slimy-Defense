public class Snail extends Enemy {

    public Snail(int health, double speed, int coinsOnKill, boolean isHidden, Path path) {
        super(health, speed, coinsOnKill, isHidden, path);
    }

    @Override
    public void move() {
        // Implement the movement logic specific to Snail
        currentPosition.setX(currentPosition.getX() + (int)speed);
        System.out.println("Snail moves with speed: " + this.speed);
    }

    @Override
    public void attack(Base base) {
        // Implement the attack logic specific to Snail
        base.decreaseHealth(health); // Example: Decrease base health by 2
        System.out.println("Snail attacks.");
    }

    @Override
    public void render() {
        // Logic to render the Snail
        System.out.println("Rendering Snail at position: " + currentPosition);
    }
}
