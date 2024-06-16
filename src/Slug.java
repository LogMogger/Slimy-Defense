public class Slug extends Enemy {

    public Slug(int health, double speed, int coinsOnKill, boolean isHidden, Path path) {
        super(health, speed, coinsOnKill, isHidden, path);
    }

    @Override
    public void move() {
        // Implement the movement logic specific to Slug
        // For simplicity, let's just move the slug horizontally
        currentPosition.setX(currentPosition.getX() + (int)speed);
        System.out.println("Slug moves with speed: " + this.speed);
    }

    @Override
    public void attack(Base base) {
        // Implement the attack logic specific to Slug
        // For simplicity, let's just decrease the base health
        base.decreaseHealth(1); // Example: Decrease base health by 1
        System.out.println("Slug attacks.");
    }

    @Override
    public void render() {
        // Logic to render the Slug
        System.out.println("Rendering Slug at position: " + currentPosition);
    }
}
