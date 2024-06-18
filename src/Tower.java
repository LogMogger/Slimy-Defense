public abstract class Tower {
    protected int level;
    protected int damage;
    protected double fireRate;
    protected double range;
    protected double splashAOE;
    protected int upgradeCost;
    protected boolean canDetectHidden;
    protected Location location;

    public Tower(int damage, double fireRate, double range, double splashAOE, int upgradeCost, boolean canDetectHidden) {
        this.level = 1;
        this.damage = damage;
        this.fireRate = fireRate;
        this.range = range;
        this.splashAOE = splashAOE;
        this.upgradeCost = upgradeCost;
        this.canDetectHidden = canDetectHidden;
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public double getFireRate() {
        return fireRate;
    }

    public double getRange() {
        return range;
    }

    public double getSplashAOE() {
        return splashAOE;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public boolean canDetectHidden() {
        return canDetectHidden;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void upgrade() {
        this.level++;
        this.damage *= 1;
        this.fireRate *= 1;
        this.range += 1;
        this.splashAOE *= 1;
        this.upgradeCost *= 1;
        this.canDetectHidden = this.canDetectHidden || (level >= 3);
    }

    public abstract void attack();
}
