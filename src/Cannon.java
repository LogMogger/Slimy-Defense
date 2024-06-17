public class Cannon extends Tower {
    public Cannon() {
        super(100, 2.0, 7, 0.0, 500, false);
    }

    @Override
    public void attack() {
    }

    @Override
    public void upgrade() {
        if (this.level >= 4) return;
        super.upgrade();
        if (this.level == 2) {
            this.damage = 180;
            this.fireRate = 2.0;
            this.range = 8;
            this.upgradeCost = 3600;
        } else if (this.level == 3) {
            this.damage = 550;
            this.fireRate = 1.6;
            this.range = 12;
            this.upgradeCost = 7800;
        } else if (this.level == 4) {
            this.damage = 1400;
            this.fireRate = 1.5;
            this.range = 18;
        }
    }
}