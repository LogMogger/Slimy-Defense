public class Archer extends Tower {
    public Archer() {
        super(8, 2.0, 10, 0.00, 15, false);
    }

    @Override
    public void attack() {
        System.out.println("Archer damage: " + this.damage);
    }

    @Override
    public void upgrade() {
        if (this.level >= 4) return; // Cap at level 4
        super.upgrade();
        if (this.level == 2) {
            this.damage = 10;
            this.fireRate = 1.6;
            this.range = 12;
            this.upgradeCost = 50;
        } else if (this.level == 3) {
            this.damage = 16;
            this.fireRate = 1.6;
            this.range = 14;
            this.upgradeCost = 500;
            this.canDetectHidden = true;
        } else if (this.level == 4) {
            this.damage = 50;
            this.fireRate = 1.0;
            this.range = 16;
        }
    }
}