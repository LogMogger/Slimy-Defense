public class Gladiator extends Tower {
    public Gladiator() {
        super(12, 1.6, 2, 0.0, 10, false);
    }

    @Override
    public void attack() {
    }

    @Override
    public void upgrade() {
        if (this.level >= 4) return;
        super.upgrade();
        if (this.level == 2) {
            this.damage = 12;
            this.fireRate = 1.2;
            this.range = 2.5;
            this.upgradeCost = 25;
        } else if (this.level == 3) {
            this.damage = 20;
            this.fireRate = 1.2;
            this.range = 2.5;
            this.upgradeCost = 1000;
            this.canDetectHidden = true;
        } else if (this.level == 4) {
            this.damage = 485;
            this.fireRate = 1.0;
            this.range = 3;
        }
    }
}