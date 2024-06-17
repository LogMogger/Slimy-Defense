public class FireMage extends Tower {
    public FireMage() {
        super(4, 0.1, 5, 0.0, 500, true);
    }

    @Override
    public void attack() {
    }

    @Override
    public void upgrade() {
        if (this.level >= 4) return;
        super.upgrade();
        if (this.level == 2) {
            this.damage = 8;
            this.fireRate = 0.1;
            this.range = 8;
            this.upgradeCost = 1000;
        } else if (this.level == 3) {
            this.damage = 20;
            this.fireRate = 0.1;
            this.range = 10;
            this.upgradeCost = 4000;
        } else if (this.level == 4) {
            this.damage = 45;
            this.fireRate = 0.1;
            this.range = 20;
        }
    }
}