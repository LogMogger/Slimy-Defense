public abstract class Enemy {
    protected int health;
    protected double speed;
    protected int coinsOnKill;
    protected boolean isHidden;
    protected Path path;
    protected Location currentPosition;

    public Enemy(int health, double speed, int coinsOnKill, boolean isHidden, Path path) {
        this.health = health;
        this.speed = speed;
        this.coinsOnKill = coinsOnKill;
        this.isHidden = isHidden;
        this.path = path;
        this.currentPosition = path.getStart();
    }

    public Location getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Location location) {
        this.currentPosition = location;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public abstract void move();
    public abstract void attack(Base base);
    public abstract void render();
}
