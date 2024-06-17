public class Bomber extends Tower {
    public Bomber() {
        super(10, 4.0, 6, 1.5, 125, false);
    }

    @Override
    public void attack() {
    }

    @Override
    public void upgrade() {
        if (this.level >= 4) return; // Cap at level 4
        super.upgrade();
        if (this.level == 2) {
            this.damage = 30;
            this.fireRate = 4.0;
            this.range = 7.0;
            this.splashAOE = 2.0;
            this.upgradeCost = 1600;
        } else if (this.level == 3) {
            this.damage = 140;
            this.fireRate = 2.0;
            this.range = 8;
            this.splashAOE = 3.0;
            this.upgradeCost = 1050;
        } else if (this.level == 4) {
            this.damage = 225;
            this.fireRate = 2.0;
            this.range = 10;
            this.splashAOE = 3.5;
            this.canDetectHidden = true;
        }
    }
}