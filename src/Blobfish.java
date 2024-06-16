public class Blobfish extends Enemy {

    public Blobfish(int health, double speed, int coinsOnKill, boolean isHidden, Path path) {
        super(health, speed, coinsOnKill, isHidden, path);
    }

    @Override
    public void move() {
        // Implement the movement logic specific to Blobfish
        currentPosition.setX(currentPosition.getX() + (int)speed);
        System.out.println("Blobfish moves with speed: " + this.speed);
    }

    @Override
    public void attack(Base base) {
        // Implement the attack logic specific to Blobfish
        base.decreaseHealth(5); // Example: Decrease base health by 5
        System.out.println("Blobfish attacks.");
    }

    @Override
    public void render() {
        // Logic to render the Blobfish
        System.out.println("Rendering Blobfish at position: " + currentPosition);
    }
}
