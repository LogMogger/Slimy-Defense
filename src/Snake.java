public class Snake extends Enemy {

    public Snake(int health, double speed, int coinsOnKill, boolean isHidden, Path path) {
        super(health, speed, coinsOnKill, isHidden, path);
    }

    @Override
    public void move() {
        // Implement the movement logic specific to Snake
        currentPosition.setX(currentPosition.getX() + (int)speed);
        System.out.println("Snake moves with speed: " + this.speed);
    }

    @Override
    public void attack(Base base) {
        // Implement the attack logic specific to Snake
        base.decreaseHealth(4); // Example: Decrease base health by 4
        System.out.println("Snake attacks.");
    }

    @Override
    public void render() {
        // Logic to render the Snake
        System.out.println("Rendering Snake at position: " + currentPosition);
    }
}
